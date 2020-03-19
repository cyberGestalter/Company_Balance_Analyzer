package model.models;

//Данные анализа прочих финансовых показателей
public class FinPerformanceAnalysisResult {
    //1) B/V и p/b
    //2) PM
    //3) SPS и p/s
    //4) ROE
    //5) Динамика ROE
    //6) DOE
    //7) CR
    //8) QR
    //9) p/c
    private String ticker;
    private int year;
    private String bookValueEvaluation;
    private String profitMarginEvaluation;
    private String salesPerShareEvaluation;
    private String roeEvaluation;
    private String roeDynamics;
    private String doeEvaluation;
    private String crEvaluation;
    private String qrEvaluation;
    private String priceToCashEvaluation;

    //Составляющая суммарной оценки компании
    private int promise;

    public int getPromise() {
        return promise;
    }

    public void setPromise(int promise) {
        this.promise = this.promise + promise;
    }

    public FinPerformanceAnalysisResult() {
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

    public String getBookValueEvaluation() {
        return bookValueEvaluation;
    }

    public void setBookValueEvaluation(String bookValueEvaluation) {
        this.bookValueEvaluation = bookValueEvaluation;
    }

    public String getProfitMarginEvaluation() {
        return profitMarginEvaluation;
    }

    public void setProfitMarginEvaluation(String profitMarginEvaluation) {
        this.profitMarginEvaluation = profitMarginEvaluation;
    }

    public String getSalesPerShareEvaluation() {
        return salesPerShareEvaluation;
    }

    public void setSalesPerShareEvaluation(String salesPerShareEvaluation) {
        this.salesPerShareEvaluation = salesPerShareEvaluation;
    }

    public String getRoeEvaluation() {
        return roeEvaluation;
    }

    public void setRoeEvaluation(String roeEvaluation) {
        this.roeEvaluation = roeEvaluation;
    }

    public String getRoeDynamics() {
        return roeDynamics;
    }

    public void setRoeDynamics(String roeDynamics) {
        this.roeDynamics = roeDynamics;
    }

    public String getDoeEvaluation() {
        return doeEvaluation;
    }

    public void setDoeEvaluation(String doeEvaluation) {
        this.doeEvaluation = doeEvaluation;
    }

    public String getCrEvaluation() {
        return crEvaluation;
    }

    public void setCrEvaluation(String crEvaluation) {
        this.crEvaluation = crEvaluation;
    }

    public String getQrEvaluation() {
        return qrEvaluation;
    }

    public void setQrEvaluation(String qrEvaluation) {
        this.qrEvaluation = qrEvaluation;
    }

    public String getPriceToCashEvaluation() {
        return priceToCashEvaluation;
    }

    public void setPriceToCashEvaluation(String priceToCashEvaluation) {
        this.priceToCashEvaluation = priceToCashEvaluation;
    }
}
