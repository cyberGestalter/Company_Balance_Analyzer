package model.models;

public class AnalysisCheck {
    //Проверка оценки - корреляция между изменением прибылей и цен акций. Если хорошие показатели прибыли,
    //но акции не растут, что-то не так
    //Используется коэффициент корреляции Пирсона
    private String ticker;
    private double pearsonCorrelation;

    public AnalysisCheck() {
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getPearsonCorrelation() {
        return pearsonCorrelation;
    }

    public void setPearsonCorrelation(double pearsonCorrelation) {
        this.pearsonCorrelation = pearsonCorrelation;
    }
}
