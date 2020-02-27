package model;

import model.models.AnalysisResult;
import model.models.CalculatedData;
import model.models.Company;
import model.models.DataForCalculation;
import model.service.SharesDBManipulator;
import model.service.analizer.ParsedDataAnalizer;
import model.service.parser.investing.CompanyParser;
import model.service.parser.investing.DataForCalculationParser;

import java.sql.SQLException;
import java.util.List;

//Класс для доступа ко всем элементам модели
public class Model {
    private CompanyParser companyParser;
    private DataForCalculationParser dataForCalculationParser;
    private SharesDBManipulator sharesDBManipulator;
    private ParsedDataAnalizer parsedDataAnalizer;

    //Если true - таблицы в БД уже созданы, false - таблицы в БД не созданы
    public Model(boolean areTablesCreated) throws SQLException, ClassNotFoundException {
        this.companyParser = new CompanyParser();
        this.dataForCalculationParser = new DataForCalculationParser();
        this.sharesDBManipulator = new SharesDBManipulator();
        if (!areTablesCreated) this.sharesDBManipulator.createTablesAndForeignKeys();
        this.parsedDataAnalizer = new ParsedDataAnalizer();
    }

    //Анализ одной компании по адресу веб-страницы и запись всех промежуточных и конечных результатов в БД
    // и конечных результатов в файл
    public void analyse(String searchString) throws SQLException {
        List<Company> companies = companyParser.getCompanies(searchString);
        sharesDBManipulator.writeDataAboutCompanyToDB(companies);
        List<DataForCalculation> dataForCalculations = dataForCalculationParser.getDataForCalculation(searchString);
        sharesDBManipulator.writeDataAboutDataForCalculationToDB(dataForCalculations);
        List<CalculatedData> calculatedData = parsedDataAnalizer.calculate(dataForCalculations);
        sharesDBManipulator.loadCalculatedDataToDB(calculatedData);
        List<AnalysisResult> analysisResults = parsedDataAnalizer.getAnalysisResults(calculatedData);
        sharesDBManipulator.loadAnalysisResultsToDB(analysisResults);
        String ticker = companies.get(0).getTicker();
        double pearsonCorrelation = parsedDataAnalizer.getPriceEarnSpeedCorrelation(calculatedData);
        sharesDBManipulator.writeAnalysisResultsToFile(analysisResults, pearsonCorrelation);
        sharesDBManipulator.loadPearsonCorrelationToDB(ticker, pearsonCorrelation);
    }

    //Анализ одной компании из БД
    public void analyseFromDB(String searchString) throws SQLException {
        List<DataForCalculation> dataForCalculations = sharesDBManipulator.getDataForAnalysisFromDB(searchString);
        List<CalculatedData> calculatedData = parsedDataAnalizer.calculate(dataForCalculations);
        sharesDBManipulator.loadCalculatedDataToDB(calculatedData);
        List<AnalysisResult> analysisResults = parsedDataAnalizer.getAnalysisResults(calculatedData);
        sharesDBManipulator.loadAnalysisResultsToDB(analysisResults);
        String ticker = dataForCalculations.get(0).getTicker();
        double pearsonCorrelation = parsedDataAnalizer.getPriceEarnSpeedCorrelation(calculatedData);
        sharesDBManipulator.loadPearsonCorrelationToDB(ticker, pearsonCorrelation);
        sharesDBManipulator.writeAnalysisResultsToFile(analysisResults, pearsonCorrelation);
    }

    //Выгрузка результата из БД в файл
    //Если false - ТОЛЬКО с относительной оценкой перспективности
    //Если true - все данные по каждому результату из БД
    public void getEvaluationOfCompanyFromDB(String searchString, boolean isCompleteResult) throws SQLException {
        List<AnalysisResult> analysisResults = sharesDBManipulator.getAnalysisResultFromDB(searchString, isCompleteResult);
        double pearsonCorrelation = sharesDBManipulator.getPearsonCorrelationFromDB(searchString).getPearsonCorrelation();
        sharesDBManipulator.writeAnalysisResultsToFile(analysisResults, pearsonCorrelation);
    }

    public CompanyParser getCompanyParser() {
        return companyParser;
    }

    public void setCompanyParser(CompanyParser companyParser) {
        this.companyParser = companyParser;
    }

    public DataForCalculationParser getDataForCalculationParser() {
        return dataForCalculationParser;
    }

    public void setDataForCalculationParser(DataForCalculationParser dataForCalculationParser) {
        this.dataForCalculationParser = dataForCalculationParser;
    }

    public SharesDBManipulator getSharesDBManipulator() {
        return sharesDBManipulator;
    }

    public void setSharesDBManipulator(SharesDBManipulator sharesDBManipulator) {
        this.sharesDBManipulator = sharesDBManipulator;
    }

    public ParsedDataAnalizer getParsedDataAnalizer() {
        return parsedDataAnalizer;
    }

    public void setParsedDataAnalizer(ParsedDataAnalizer parsedDataAnalizer) {
        this.parsedDataAnalizer = parsedDataAnalizer;
    }
}
