package model.models;

//Анализ дивидендов - критерий для выбора компаний в ОДНОЙ отрасли
//1) Стабильная история выплат
//2) Сравнение со средним дивидендным выходом по отрасли
//share_roe, dividend_payout
public class DividendsAnalysisResult {
    private String ticker;
    private String historyStability; //"хорошая"/ "плохая"
    private String industryLessMore; //"хорошо" - меньше PR по отрасли/ "плохо" - больше PR по отрасли

    //Составляющая суммарной оценки компании
    private int promise;

    public int getPromise() {
        return promise;
    }

    public void setPromise(int promise) {
        this.promise = this.promise + promise;
    }

    public DividendsAnalysisResult() {
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getHistoryStability() {
        return historyStability;
    }

    public void setHistoryStability(String historyStability) {
        this.historyStability = historyStability;
    }

    public String getIndustryLessMore() {
        return industryLessMore;
    }

    public void setIndustryLessMore(String industryLessMore) {
        this.industryLessMore = industryLessMore;
    }

}
