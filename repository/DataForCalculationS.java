package model.repository;

import java.sql.SQLException;

public class DataForCalculationS extends BaseTable implements TableOperations {
    public DataForCalculationS() throws SQLException {
        super("data_for_calculation");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS data_for_calculation\n" +
                "(\n" +
                "  ticker varchar(15) NOT NULL,\n" +
                "  year bigint,\n" +
                "  date_of_report varchar(15),\n" +
                "  price double COMMENT 'Price - Цена акции',\n" +
                "  dividend double COMMENT 'Dividend - Дивиденд',\n" +
                "  net_income double COMMENT 'Net income - Чистая прибыль',\n" +
                "  total_revenue double COMMENT 'Total revenue - Выручка (объем продаж)',\n" +
                "  total_equity double COMMENT 'Total equity - Собственный капитал',\n" +
                "  total_common_shares_outstanding double COMMENT 'Total Common Shares Outstanding - Число обыкновенных акций',\n" +
                "  total_preferred_shares_outstanding double DEFAULT 0 COMMENT 'Total Preferred Shares Outstanding - Число привилегированных акций',\n" +
                "  total_liabilities double COMMENT 'Total liabilities - Долгосрочные долговые обязательства',\n" +
                "  total_current_liabilities double COMMENT 'Total current liabilities - Краткосрочные долговые (текущие) обязательства',\n" +
                "  current_assets double COMMENT 'Current assets - текущие активы',\n" +
                "  total_inventory double COMMENT 'Total inventory - Материальные запасы',\n" +
                "  cash double COMMENT 'Cash - Наличный капитал'\n" +
                ");", "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {
        super.executeSqlStatement("ALTER TABLE data_for_calculation ADD CONSTRAINT fk_data_for_calculation_\n" +
                "  FOREIGN KEY (ticker) REFERENCES companies (ticker);",
                "Cоздан внешний ключ data_for_calculation.ticker -> companies.ticker");
    }
}
