package model.models;

//Анализ растущих компаний из новых развивающихся отраслей
public class GrowCompanyAnalysisResult {
    //Избегать компаний, у которых Р/Е больше скорости роста прибылей за год в процентах
    //Если Р/Е больше 25 даже при большом темпе роста прибылей, продавать при первых признаках падения рынка
    //Больше или меньше справедливой цены
    //Справедливая цена акций

    private String ticker;
    private int year;
    private String peEarnSpeedCompare;
    private String buyOrSellEvaluation;
    private String equitablePrice;

    //Составляющая суммарной оценки компании
    private int promise;

    public int getPromise() {
        return promise;
    }

    public void setPromise(int promise) {
        this.promise = this.promise + promise;
    }

    public GrowCompanyAnalysisResult() {
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

    public String getPeEarnSpeedCompare() {
        return peEarnSpeedCompare;
    }

    public void setPeEarnSpeedCompare(String peEarnSpeedCompare) {
        this.peEarnSpeedCompare = peEarnSpeedCompare;
    }

    public String getBuyOrSellEvaluation() {
        return buyOrSellEvaluation;
    }

    public void setBuyOrSellEvaluation(String buyOrSellEvaluation) {
        this.buyOrSellEvaluation = buyOrSellEvaluation;
    }

    public String getEquitablePrice() {
        return equitablePrice;
    }

    public void setEquitablePrice(String equitablePrice) {
        this.equitablePrice = equitablePrice;
    }
}
