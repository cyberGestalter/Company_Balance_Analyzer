package model.models;

//Класс, описывающий компанию, выпустившую акции
public class Company extends BaseModel {
    /*
    Компания
    Тикер
    Отрасль
    */
    //Название компании
    private String companyName;
    //Обозначение акций компании на бирже
    private String ticker;
    //Отрасль компании
    private String companyIndustry;

    public Company() {}

    public Company(String companyName, String ticker, String companyIndustry) {
        this.companyName = companyName;
        this.ticker = ticker;
        this.companyIndustry = companyIndustry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }
}
