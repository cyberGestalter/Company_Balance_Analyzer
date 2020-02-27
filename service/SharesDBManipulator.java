package model.service;

import model.models.*;
import model.repository.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Координатор классов пакета repository и models
public class SharesDBManipulator {

    private static final String DB_URL = "jdbc:mysql://localhost/";
    private static final String DB_INVESTMENTS = "Название БД?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    private static final String RESULT_File = "Адрес файла для сохранения суммарной оценки";
    //Таблицы БД
    public Companies companies;
    private DataForCalculationS dataForCalculationS;
    private CalculatedDataS calculatedDataS;
    private InvestAnalysisResult investAnalysisResult;
    private AnalysisCheckS analysisCheckS;

    public SharesDBManipulator(boolean renew) throws SQLException, ClassNotFoundException {
        Class.forName(DB_Driver);
        this.companies = new Companies();
        this.dataForCalculationS = new DataForCalculationS();
        this.calculatedDataS = new CalculatedDataS();
        this.investAnalysisResult = new InvestAnalysisResult();
        this.analysisCheckS = new AnalysisCheckS();
    }

    public SharesDBManipulator() throws SQLException, ClassNotFoundException {
        this(false);
    }

    // Получить новое соединение с БД
    public static Connection getConnection() throws SQLException {
        //Параметры доступа - логин и пароль
        String user = "логин";
        String password = "пароль";
        return DriverManager.getConnection(DB_URL + DB_INVESTMENTS, user, password);
    }

    //Создание всех таблиц и внешних ключей
    public void createTablesAndForeignKeys() throws SQLException {
        companies.createTable();
        dataForCalculationS.createTable();
        calculatedDataS.createTable();
        investAnalysisResult.createTable();
        analysisCheckS.createTable();
       
        dataForCalculationS.createForeignKeys();
        calculatedDataS.createForeignKeys();
        investAnalysisResult.createForeignKeys();
        analysisCheckS.createForeignKeys();
    }

    //Запись в базу данных инфы, описывающей компанию, выпустившую акции
    //Эту инфу получает с сайта CompanyParser
    public void writeDataAboutCompanyToDB(List<Company> companiesListAfterParsing) throws SQLException {
        Statement statement = createLocalStatement();
        for (Company company : companiesListAfterParsing) {
            String companyName = company.getCompanyName();
            String shareTicker = company.getTicker();
            String companyIndustry = company.getCompanyIndustry();
            statement.execute(String.format("INSERT INTO companies VALUES ('%s', '%s', '%s');",
                    shareTicker, companyName, companyIndustry));
        }
        statement.close();
    }

    //Запись в базу данных данных для расчетов
    //Эту инфу получает с сайта DataForCalculationParser
    public void writeDataAboutDataForCalculationToDB(List<DataForCalculation> dataForCalculationsListAfterParsing) throws SQLException {
        Statement statement = createLocalStatement();
        for (DataForCalculation dataForCalculation : dataForCalculationsListAfterParsing) {
            String ticker = dataForCalculation.getTicker();
            int year = dataForCalculation.getYear();
            String dateOfReport = dataForCalculation.getDateOfReport();
            double price = dataForCalculation.getPrice();
            double dividend = dataForCalculation.getDividend();
            double netIncome = dataForCalculation.getNetIncome();
            double totalRevenue = dataForCalculation.getTotalRevenue();
            double totalEquity = dataForCalculation.getTotalEquity();
            double totalCommonSharesOutstanding = dataForCalculation.getTotalCommonSharesOutstanding();
            double totalPreferredSharesOutstanding = dataForCalculation.getTotalPreferredSharesOutstanding();
            double totalLiabilities = dataForCalculation.getTotalLiabilities();
            double totalCurrentLiabilities = dataForCalculation.getTotalCurrentLiabilities();
            double currentAssets = dataForCalculation.getCurrentAssets();
            double totalInventory = dataForCalculation.getTotalInventory();
            double cash = dataForCalculation.getCash();
            statement.execute("INSERT INTO data_for_calculation (ticker, year, date_of_report, price, dividend, " +
                            "net_income, total_revenue, total_equity, total_common_shares_outstanding, total_preferred_shares_outstanding," +
                            "total_liabilities, total_current_liabilities, current_assets, total_inventory, cash) VALUES " +
                            "('"+ticker+"', "+year+", '"+dateOfReport+"', "+price+", "+dividend+", "+netIncome+", "+totalRevenue+", "+totalEquity+", "+totalCommonSharesOutstanding+", " +
                            ""+totalPreferredSharesOutstanding+", "+totalLiabilities+", "+totalCurrentLiabilities+", "+currentAssets+", "+totalInventory+", "+cash+");");
        }
        statement.close();
    }

    //Выгрузка инфы о компаниях из БД и сохранение ее в companiesFromDB
    public List<Company> getDataAboutCompaniesFromDB() throws SQLException {
        List<Company> companiesFromDB = new ArrayList<>();
        Statement statement = createLocalStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM companies;");
        int colNum = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            Company company = new Company();
            company.setTicker(resultSet.getString("ticker"));
            company.setCompanyName(resultSet.getString("company_name"));
            company.setCompanyIndustry(resultSet.getString("industry"));
            companiesFromDB.add(company);
        }
        return companiesFromDB;
    }

    //Выгрузка показателей компаний из БД и сохранение их в dataForAnalysis для конкретного тикера
    public List<DataForCalculation> getDataForAnalysisFromDB(String searchTicker) throws SQLException {
        List<DataForCalculation> dataForAnalysis = new ArrayList<>();
        Statement statement = createLocalStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM data_for_calculation WHERE ticker = '"+searchTicker+"';");
        int colNum = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            DataForCalculation dataForCalculation = new DataForCalculation();
            dataForCalculation.setTicker(resultSet.getString("ticker"));
            dataForCalculation.setYear(resultSet.getInt("year"));
            dataForCalculation.setDateOfReport(resultSet.getString("date_of_report"));
            dataForCalculation.setPrice(resultSet.getDouble("price"));
            dataForCalculation.setDividend(resultSet.getDouble("dividend"));
            dataForCalculation.setNetIncome(resultSet.getDouble("net_income"));
            dataForCalculation.setTotalRevenue(resultSet.getDouble("total_revenue"));
            dataForCalculation.setTotalEquity(resultSet.getDouble("total_equity"));
            dataForCalculation.setTotalCommonSharesOutstanding(resultSet.getDouble("total_common_shares_outstanding"));
            dataForCalculation.setTotalPreferredSharesOutstanding(resultSet.getDouble("total_preferred_shares_outstanding"));
            dataForCalculation.setTotalLiabilities(resultSet.getDouble("total_liabilities"));
            dataForCalculation.setTotalCurrentLiabilities(resultSet.getDouble("total_current_liabilities"));
            dataForCalculation.setCurrentAssets(resultSet.getDouble("current_assets"));
            dataForCalculation.setTotalInventory(resultSet.getDouble("total_inventory"));
            dataForCalculation.setCash(resultSet.getDouble("cash"));

            dataForAnalysis.add(dataForCalculation);
        }

        return dataForAnalysis;
    }

    //Выгрузка показателей компаний из БД и сохранение их в dataForAnalysis для каждой компании из БД
    public List<DataForCalculation> getDataForAnalysisFromDBForAllCompanies(List<Company> companies) throws SQLException {
        List<DataForCalculation> dataForAnalysisForAllCompanies = new ArrayList<>();
        List<DataForCalculation> dataForAnalysisForOneCompany = new ArrayList<>();
        for (Company company : companies) {
            dataForAnalysisForOneCompany = getDataForAnalysisFromDB(company.getTicker());
            dataForAnalysisForAllCompanies.addAll(dataForAnalysisForOneCompany);
        }
        return dataForAnalysisForAllCompanies;
    }

    //Выгрузка результатов для одной из компаний из БД
    //Если true - выгрузить данные полностью, если false - выгрузить только относительную оценку перспективности
    public List<AnalysisResult> getAnalysisResultFromDB(String searchTicker, boolean isAll) throws SQLException {
        List<AnalysisResult> analysisResults = new ArrayList<>();
        Statement statement = createLocalStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM analysis_results WHERE ticker = '"+searchTicker+"';");
        int colNum = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            AnalysisResult analysisResult = new AnalysisResult();
            analysisResult.setTicker(resultSet.getString("ticker"));
            analysisResult.setYear(resultSet.getInt("year"));
            analysisResult.setPromise(resultSet.getInt("promise_evaluation"));

            if (isAll) {
                analysisResult.setHistoryStability(resultSet.getString("dividends_history_stability"));
                analysisResult.setIndustryLessMore(resultSet.getString("dividends_with_industry_comparation"));
                analysisResult.setPriceToEPSvalueEvaluation(resultSet.getString("price_to_EPS_evaluation"));
                analysisResult.setRevenueDynamics(resultSet.getString("total_revenue_dynamics"));
                analysisResult.setEarningRevenueComparing(resultSet.getString("earnings_revenues_comparation"));
                analysisResult.setCapitalization(resultSet.getDouble("capitalization"));
                analysisResult.setBookValueEvaluation(resultSet.getString("book_value_evaluation"));
                analysisResult.setProfitMarginEvaluation(resultSet.getString("pm_evaluation"));
                analysisResult.setSalesPerShareEvaluation(resultSet.getString("sps_evaluation"));
                analysisResult.setRoeEvaluation(resultSet.getString("roe_evaluation"));
                analysisResult.setRoeDynamics(resultSet.getString("roe_dynamics"));
                analysisResult.setDoeEvaluation(resultSet.getString("doe_evaluation"));
                analysisResult.setCrEvaluation(resultSet.getString("cr_evaluation"));
                analysisResult.setQrEvaluation(resultSet.getString("qr_evaluation"));
                analysisResult.setPriceToCashEvaluation(resultSet.getString("price_to_cash_evaluation"));
                analysisResult.setPeEarnSpeedCompare(resultSet.getString("price_to_eps_earn_speed_rate_comparation"));
                analysisResult.setBuyOrSellEvaluation(resultSet.getString("buy_or_sell_evaluation"));
                analysisResult.setEquitablePrice(resultSet.getString("equitable_price"));
            }

            analysisResults.add(analysisResult);
        }
        return analysisResults;
    }

    //Выгрузка из БД коэффициента корреляции между ценами акций и динамикой прибылей для одной из компаний в БД
    public AnalysisCheck getPearsonCorrelationFromDB(String ticker) throws SQLException {
        AnalysisCheck analysisCheck = new AnalysisCheck();
        Statement statement = createLocalStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM analysis_check WHERE ticker = '"+ticker+"';");
        int colNum = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            analysisCheck.setTicker(resultSet.getString("ticker"));
            analysisCheck.setPearsonCorrelation(resultSet.getDouble("price_earn_correlation"));
        }
        return analysisCheck;
    }

    //Загрузка посчитанных данных в БД
    public void loadCalculatedDataToDB(List<CalculatedData> calculatedDatas) throws SQLException {
        Statement statement = createLocalStatement();
        for (CalculatedData calculatedData : calculatedDatas) {
            statement.execute("INSERT INTO calculated_data VALUES ('"+calculatedData.getTicker()+"', "+calculatedData.getYear()+", "+calculatedData.getEps()+"" +
                    ", "+calculatedData.getCapitalization()+", "+calculatedData.getPriceToEPS()+", "+calculatedData.getIncomeSpeedRate()+", "+calculatedData.getEquitablePrice()+"" +
                    ", "+calculatedData.getDividendPayout()+", "+calculatedData.getIndustryDividendPayout()+", "+calculatedData.getShare_ROE()+", "+calculatedData.getEpsSpeedRate()+"" +
                    ", "+calculatedData.getTotalRevenueSpeedRate()+", "+calculatedData.getBookValue()+", "+calculatedData.getPriceToBookValue()+", "+calculatedData.getProfitMargin()+"" +
                    ", "+calculatedData.getSalesPerShare()+", "+calculatedData.getPriceToSPS()+", "+calculatedData.getRoe()+", "+calculatedData.getDoe()+", "+calculatedData.getCurrentRatio()+"" +
                    ", "+calculatedData.getQuickRatio()+", "+calculatedData.getPriceToCash()+");");
        }
        statement.close();
    }

    //Загрузка оцененных данных в БД 
    //Для каждого тикера оценка дивидендов одинаковая, поэтому для дивидендов одной компании в каждом году будут одинаковые оценки
    public void loadAnalysisResultsToDB(List<AnalysisResult> analysisResults) throws SQLException{
        Statement statement = createLocalStatement();
        for (AnalysisResult analysisResult : analysisResults) {
            statement.execute("INSERT INTO analysis_results SET ticker='"+ analysisResult.getTicker()
                    +"', year= "+analysisResult.getYear()+", promise_evaluation = "+analysisResult.getPromise()+", dividends_history_stability = '"+analysisResult.getHistoryStability()+"', " +
                    "dividends_with_industry_comparation = '"+analysisResult.getIndustryLessMore()+"'"+", price_to_EPS_evaluation = '"+analysisResult.getPriceToEPSvalueEvaluation()+"', " +
                    "total_revenue_dynamics = '"+analysisResult.getRevenueDynamics()+"', earnings_revenues_comparation = '"+analysisResult.getEarningRevenueComparing()+"', " +
                    "capitalization = "+analysisResult.getCapitalization()+", book_value_evaluation='"+analysisResult.getBookValueEvaluation()+"', pm_evaluation = '" +
                    ""+analysisResult.getProfitMarginEvaluation()+"', " + "sps_evaluation = '"+analysisResult.getSalesPerShareEvaluation()+"', roe_evaluation = '"+analysisResult.getRoeEvaluation()+"'" +
                    ", roe_dynamics = '"+analysisResult.getRoeDynamics()+"', doe_evaluation = '"+analysisResult.getDoeEvaluation()+"', cr_evaluation = '"+analysisResult.getCrEvaluation()+"', " +
                    "qr_evaluation = '"+analysisResult.getQrEvaluation()+"', price_to_cash_evaluation = '"+analysisResult.getPriceToCashEvaluation()+"', " +
                    "price_to_eps_earn_speed_rate_comparation = '"+analysisResult.getPeEarnSpeedCompare()+"', buy_or_sell_evaluation = '"+analysisResult.getBuyOrSellEvaluation()+"', " +
                    "equitable_price = '"+analysisResult.getEquitablePrice()+"';");
        }
    }

    //Загрузка информации о коэффициенте корреляции в БД
    public void loadPearsonCorrelationToDB(String ticker, double pearsonCorrelation) throws SQLException {
        Statement statement = createLocalStatement();
        statement.execute("INSERT INTO analysis_check SET ticker ='"+ticker+"', price_earn_correlation = "+pearsonCorrelation+";");
    }

    //Запись данных об оценке ОДНОЙ компании в файл без перезаписи файла
    public void writeAnalysisResultsToFile(List<AnalysisResult> analysisResults, double pearsonCorrelation) {
        try (FileOutputStream fos = new FileOutputStream(RESULT_File, true); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {
            for (AnalysisResult analysisResult : analysisResults) {
                writer.write(analysisResult.getTicker()+" "+analysisResult.getYear()+" Перспективность: "+analysisResult.getPromise()+"\n");
            }
            writer.write("Зависимость между ценой акций и прибылями: "+pearsonCorrelation+"\n");
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Создание коннекта с БД
    private Statement createLocalStatement() throws SQLException {
        Connection connection = getConnection();
        return connection.createStatement();
    }
}
