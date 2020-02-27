package model.service.analizer;

import model.models.*;

import java.util.ArrayList;
import java.util.List;

//Берет информацию из базы данных, анализирует и возвращает в нее результат
//в таблицу calculated_data
public class ParsedDataAnalizer {

    public ParsedDataAnalizer() {
    }

    //Расчет финансовых показателей для каждой компании из переданного списка и их запись в calculatedDatas
    public List<CalculatedData> calculate(List<DataForCalculation> dataForCalculations) {
        List<CalculatedData> calculatedDatas = new ArrayList<>();
        for (DataForCalculation dataForCalculation : dataForCalculations) {
            CalculatedData calculatedData = new CalculatedData();
            //DataForCalculation dataForCalculation = dataForCalculations.get(i);
            calculatedData.setTicker(dataForCalculation.getTicker());
            calculatedData.setYear(dataForCalculation.getYear());
            //Для дальнейшего подсчета темпов роста
            int year = calculatedData.getYear()-1;
            double netIncomeLastYear = 0;
            double totalRevenue = 0;
            double totComSharOut = 0;
            for (DataForCalculation dataForCalculation1 : dataForCalculations) {
                if (dataForCalculation1.getTicker().equals(calculatedData.getTicker()) && dataForCalculation1.getYear() == year) {
                    netIncomeLastYear = dataForCalculation1.getNetIncome();
                    totalRevenue = dataForCalculation1.getTotalRevenue();
                    totComSharOut = dataForCalculation1.getTotalCommonSharesOutstanding();
                }
            }

            if (dataForCalculation.getTotalCommonSharesOutstanding() != 0) {
                calculatedData.setEps(dataForCalculation.getNetIncome() / dataForCalculation.getTotalCommonSharesOutstanding());
            } else {
                calculatedData.setEps(0);
            }

            calculatedData.setCapitalization(dataForCalculation.getPrice()*dataForCalculation.getTotalCommonSharesOutstanding());

            if (calculatedData.getEps() != 0) {
                calculatedData.setPriceToEPS(dataForCalculation.getPrice() / calculatedData.getEps());
            } else {
                calculatedData.setPriceToEPS(0);
            }

            //Если netIncome за предыдущий год равна 0, значит данных за предыдущий год нет
            if (netIncomeLastYear != 0) {
                calculatedData.setIncomeSpeedRate((dataForCalculation.getNetIncome()-netIncomeLastYear)/netIncomeLastYear*100);
            }

            calculatedData.setEquitablePrice(calculatedData.getIncomeSpeedRate()*calculatedData.getEps());

            if (calculatedData.getEps() != 0) {
                calculatedData.setDividendPayout(dataForCalculation.getDividend() / calculatedData.getEps() * 100);
            } else {
                calculatedData.setDividendPayout(0);
            }

            if (dataForCalculation.getPrice() != 0) {
                calculatedData.setShare_ROE(dataForCalculation.getDividend() / dataForCalculation.getPrice() * 100);
            } else {
                calculatedData.setShare_ROE(0);
            }

            //Если netIncome и число обыкновенных акций за предыдущий год равны 0, значит данных за предыдущий год нет
            if (netIncomeLastYear != 0 && totComSharOut != 0) {
                calculatedData.setEpsSpeedRate((calculatedData.getEps() - netIncomeLastYear/totComSharOut)/(netIncomeLastYear/totComSharOut)*100);
            }

            //Если totalRevenue за предыдущий год равна 0, значит данных за предыдущий год нет
            //(Total revenue(this year) - Total revenue(last year))/Total revenue(last year) * 100',\n" +
            if (totalRevenue != 0) {
                calculatedData.setTotalRevenueSpeedRate((dataForCalculation.getTotalRevenue()-totalRevenue)/totalRevenue*100);
            }

            if (dataForCalculation.getTotalCommonSharesOutstanding() != 0) {
                calculatedData.setBookValue(dataForCalculation.getTotalEquity() / dataForCalculation.getTotalCommonSharesOutstanding());
            } else {
                calculatedData.setBookValue(0);
            }

            if (calculatedData.getBookValue() != 0) {
                calculatedData.setPriceToBookValue(dataForCalculation.getPrice() / calculatedData.getBookValue());
            } else {
                calculatedData.setPriceToBookValue(0);
            }

            if (dataForCalculation.getTotalRevenue() != 0) {
                calculatedData.setProfitMargin(dataForCalculation.getNetIncome() / dataForCalculation.getTotalRevenue() * 100);
            } else {
                calculatedData.setProfitMargin(0);
            }

            if (dataForCalculation.getTotalCommonSharesOutstanding() != 0) {
                calculatedData.setSalesPerShare(dataForCalculation.getTotalRevenue() / dataForCalculation.getTotalCommonSharesOutstanding());
            } else {
                calculatedData.setSalesPerShare(0);
            }

            if (calculatedData.getSalesPerShare() != 0) {
                calculatedData.setPriceToSPS(dataForCalculation.getPrice() / calculatedData.getSalesPerShare());
            } else {
                calculatedData.setPriceToSPS(0);
            }

            if (dataForCalculation.getTotalEquity() != 0) {
                calculatedData.setRoe(dataForCalculation.getNetIncome() / dataForCalculation.getTotalEquity() * 100);
                calculatedData.setDoe(dataForCalculation.getTotalLiabilities()/dataForCalculation.getTotalEquity()*100);
            } else {
                calculatedData.setRoe(0);
                calculatedData.setDoe(0);
            }

            if (dataForCalculation.getTotalCurrentLiabilities() != 0) {
                calculatedData.setCurrentRatio(dataForCalculation.getCurrentAssets() / dataForCalculation.getTotalCurrentLiabilities());
                calculatedData.setQuickRatio((dataForCalculation.getCurrentAssets() - dataForCalculation.getTotalInventory())/dataForCalculation.getTotalCurrentLiabilities());
            } else {
                calculatedData.setCurrentRatio(0);
                calculatedData.setQuickRatio(0);
            }

            if (dataForCalculation.getCash() != 0) {
                calculatedData.setPriceToCash(dataForCalculation.getPrice() / (dataForCalculation.getCash() / dataForCalculation.getTotalCommonSharesOutstanding()));
            } else {
                calculatedData.setPriceToCash(0);
            }

            calculatedDatas.add(calculatedData);
        }
        return calculatedDatas;
    }

    //Анализ дивидендов - критерий для выбора компаний в ОДНОЙ отрасли
    public List<DividendsAnalysisResult> getDividendsAnalysis(List<CalculatedData> calculatedDatas) {
        List<DividendsAnalysisResult> divAnalysisResult = new ArrayList<>();

        for (CalculatedData calculatedData : calculatedDatas) {
            if (!containsDivResultWithTicker(divAnalysisResult, calculatedData.getTicker())) {
                DividendsAnalysisResult dividendsAnalysisResult = new DividendsAnalysisResult();
                dividendsAnalysisResult.setTicker(calculatedData.getTicker());
                //Число лет, когда выплачивались дивиденды
                int nWasDividend = 0;
                //Число лет, за которые рассматривается статистика
                int nYears = 0;
                //Число лет, когда PR превышал средний по отрасли
                int nMore = 0;
                //Число лет, когда PR был меньше среднего по отрасли
                int nLess = 0;
                for (CalculatedData calculatedData1 : calculatedDatas) {
                    if (calculatedData1.getTicker().equals(dividendsAnalysisResult.getTicker())) {
                        nYears++;
                        if (calculatedData1.getDividendPayout() > 0) nWasDividend++;
                        if (calculatedData1.getDividendPayout() > calculatedData1.getIndustryDividendPayout()) nMore++;
                        if (calculatedData1.getDividendPayout() < calculatedData1.getIndustryDividendPayout()) nLess++;
                    }
                }
                if (nYears != nWasDividend) {
                    
                    dividendsAnalysisResult.setHistoryStability("Bad - no dividends some years");
                    dividendsAnalysisResult.setPromise(0);
                } else {
                    
                    dividendsAnalysisResult.setHistoryStability("Good - there are dividends");
                    dividendsAnalysisResult.setPromise(1);
                }
                if (nMore > nLess) {
                    
                    dividendsAnalysisResult.setIndustryLessMore("Bad - no money for dividends or no information about industry dividend payout");
                    dividendsAnalysisResult.setPromise(0);
                } else {
                    
                    dividendsAnalysisResult.setIndustryLessMore("Good - company has funds");
                    dividendsAnalysisResult.setPromise(1);
                }
                divAnalysisResult.add(dividendsAnalysisResult);
            }
        }
        return divAnalysisResult;
    }

    private boolean containsDivResultWithTicker(List<DividendsAnalysisResult> list, String ticker) {
        for (DividendsAnalysisResult dividendsAnalysisResult : list) {
            if (dividendsAnalysisResult.getTicker().equals(ticker)) {
                return true;
            }
        }
        return false;
    }

    //Проверка оценки - корреляция между изменением прибылей и цен акций. Если хорошие показатели прибыли,
    //но акции не растут, что-то не так
    //Используется коэффициент корреляции Пирсона
    public double getPriceEarnSpeedCorrelation(List<CalculatedData> calculatedDatas) {
        double result = 0;
        List<Double> prices = new ArrayList<>();
        List<Double> earnSpeeds = new ArrayList<>();
        for (CalculatedData calculatedData : calculatedDatas) {
            if (calculatedData.getPriceToEPS()*calculatedData.getEps() != 0 && calculatedData.getEpsSpeedRate() != 0) {
                prices.add(calculatedData.getPriceToEPS()*calculatedData.getEps());
                earnSpeeds.add(calculatedData.getEpsSpeedRate());
            }
        }
        //На случай, если цены или прибыли не были собраны парсером
        if (prices.isEmpty() || earnSpeeds.isEmpty() || prices.size()!=earnSpeeds.size()) return 0;
        if (prices.size() == 1) return 2; //с одним значением для расчета будет NaN

        double averagePrice = getAverageValue(prices);
        double averageEarnSpeed = getAverageValue(earnSpeeds);
        double differencesSum = 0;
        for (int i = 0; i < earnSpeeds.size(); i++) {
            differencesSum += (prices.get(i)-averagePrice)*(earnSpeeds.get(i)-averageEarnSpeed);
        }
        double pricesDifSumSquare = getDifferenceSumSquare(prices, averagePrice);
        double earnSpeedsDifSumSquare = getDifferenceSumSquare(earnSpeeds, averageEarnSpeed);
        //Также возможны одинаковые значения, они тоже приведут к ошибке
        if (pricesDifSumSquare == 0 || earnSpeedsDifSumSquare == 0) return 3;
        if (differencesSum == 0 && (pricesDifSumSquare == 0 || earnSpeedsDifSumSquare == 0)) return 4;

        result = differencesSum/Math.sqrt(pricesDifSumSquare*earnSpeedsDifSumSquare);
        return result;
    }
    private double getAverageValue(List<Double> list) {
        double averageValue = 0;
        int n = 0;
        double sum = 0;
        for (Double number : list) {
            sum += number;
            n++;
        }
        averageValue = sum/n;
        return averageValue;
    }
    private double getDifferenceSumSquare(List<Double> list, double average) {
        double result = 0;
        for (Double number : list) {
            result += Math.pow((number-average),2);
        }
        return result;
    }

    //Метод для анализа посчитанных данных
    public List<AnalysisResult> getAnalysisResults(List<CalculatedData> calculatedDatas) {
        List<AnalysisResult> analysisResults = new ArrayList<>();
        /*Часть кода удалена*/
        return analysisResults;
    }


}
