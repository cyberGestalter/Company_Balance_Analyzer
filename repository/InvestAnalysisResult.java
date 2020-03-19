package model.repository;

import java.sql.SQLException;

//Запись результата анализа финансовых данных компании в базу данных
public class InvestAnalysisResult extends BaseTable implements TableOperations {
    public InvestAnalysisResult() throws SQLException {
        super("analysis_results");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS analysis_results\n" +
                "(\n" +
                "  ticker varchar(15),\n" +
                "  year bigint,\n" +
                "  promise_evaluation bigint,\n" +
                "  dividends_history_stability text,\n" +
                "  dividends_with_industry_comparation text,\n" +
                "  price_to_EPS_evaluation text,\n" +
                "  total_revenue_dynamics text,\n" +
                "  earnings_revenues_comparation text,\n" +
                "  capitalization double,\n" +
                "  book_value_evaluation text,\n" +
                "  pm_evaluation text,\n" +
                "  sps_evaluation text,\n" +
                "  roe_evaluation text,\n" +
                "  roe_dynamics text,\n" +
                "  doe_evaluation text,\n" +
                "  cr_evaluation text,\n" +
                "  qr_evaluation text,\n" +
                "  price_to_cash_evaluation text,\n" +
                "  price_to_eps_earn_speed_rate_comparation text,\n" +
                "  buy_or_sell_evaluation text,\n" +
                "  equitable_price text\n" +
                ");", "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {
        super.executeSqlStatement("ALTER TABLE analysis_results ADD CONSTRAINT fk_analysis_results_\n" +
                        "  FOREIGN KEY (ticker) REFERENCES companies (ticker);",
                "Cоздан внешний ключ analysis_results.ticker -> companies.ticker");
    }
}
