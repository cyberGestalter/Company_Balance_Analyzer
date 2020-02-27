package model.repository;

import java.sql.SQLException;

public class CalculatedDataS extends BaseTable implements TableOperations {
    public CalculatedDataS() throws SQLException {
        super("calculated_data");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS calculated_data\n" +
                "(\n" +
                "  ticker varchar(15) NOT NULL,\n" +
                "  year bigint,\n" +
                "  eps double COMMENT 'Earning per share - Прибыль на акцию за год (EPS = Чистая прибыль/Число обыкновенных акций)',\n" +
                "  capitalization double COMMENT 'Capitalization - Капитализация (= Цена акции * Число обыкновенных акций)',\n" +
                "  price_to_EPS double COMMENT 'Р/Е: CompanyShare - Price/Earning per share',\n" +
                "  income_speed_rate double COMMENT 'income speed rate - Средний годовой прирост прибыли, %: (Net income(this year) - net income(last year))/net income(last year) * 100',\n" +
                "  equitable_price double COMMENT 'equitable price - Справедливая цена акции: income speed rate * Earning per share',\n" +
                "  dividend_payout double COMMENT 'Dividend payout - Дивидендный выход(PR), %: Dividend / Earning per share * 100',\n" +
                "  industry_dividend_payout double COMMENT 'PR по отрасли, %',\n" +
                "  share_roe double COMMENT 'Рентабельность акции, %: Dividend / Price * 100',\n" +
                "  eps_speed_rate double COMMENT 'EPS speed rate - Темп роста прибыли на акцию, %: (Earning per share(this year) - Earning per share(last year))/Earning per share(last year) * 100',\n" +
                "  total_revenue_speed_rate double COMMENT 'Total revenue speed rate - Темп роста объема продаж, %: (Total revenue(this year) - Total revenue(last year))/Total revenue(last year) * 100',\n" +
                "  book_value double COMMENT 'BV - Book Value - Учетная цена акции (BV): Total equity/Total Common Shares Outstanding',\n" +
                "  price_to_book_value double COMMENT 'Price/Book Value Ratio (Stock quote rate) - Коэффициент котировки акции (p/b): Price / BV',\n" +
                "  profit_margin double COMMENT 'PM - Profit Margin - Рентабельность (РМ), %: Net income / Total revenue * 100',\n" +
                "  sps double COMMENT 'SPS - Sales Per Share - Объем продаж на одну акцию (SPS): Total revenue / Total Common Shares Outstanding',\n" +
                "  priceToSPS double COMMENT 'p/s: Price / SPS',\n" +
                "  roe double COMMENT 'ROE - Рентабельность собственного капитала (ROE), %: Net income / Total equity * 100',\n" +
                "  doe double COMMENT 'DOE - DOE, %:  Total liabilities / Total equity * 100',\n" +
                "  current_ratio double COMMENT 'Current ratio - Коэффициент покрытия(ликвидности, CR) Норма > 2: Current assets/Total current liabilities',\n" +
                "  quick_ratio double COMMENT 'Quick ratio - Коэффициент быстрой ликвидности (QR) Норма >=1: (Current assets - Total inventory)/Total current liabilities',\n" +
                "  priceToCash double COMMENT 'p/c - Цена акции к капиталу на одну акцию, р/с Норма < 0,1: Price / (Cash / Total Common Shares Outstanding)'\n" +
                ");", "Создана таблица " + tableName);

   }

    @Override
    public void createForeignKeys() throws SQLException {
        super.executeSqlStatement("ALTER TABLE calculated_data ADD CONSTRAINT fk_calculated_data_\n" +
                        "  FOREIGN KEY (ticker) REFERENCES companies (ticker);",
                "Cоздан внешний ключ calculated_data.ticker -> companies.ticker");
    }
}
