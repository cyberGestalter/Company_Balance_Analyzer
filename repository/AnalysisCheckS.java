package model.repository;

import java.sql.SQLException;

//Запись данных по корреляции между ценами и прибылями компании в базу данных
public class AnalysisCheckS extends BaseTable implements TableOperations{

    public AnalysisCheckS() throws SQLException {
        super("analysis_check");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS analysis_check\n" +
                "(\n" +
                "  ticker varchar(15) NOT NULL,\n" +
                "  price_earn_correlation double" +
                ");", "Создана таблица " + tableName);


    }

    @Override
    public void createForeignKeys() throws SQLException {
        super.executeSqlStatement("ALTER TABLE analysis_check ADD CONSTRAINT fk_analysis_check_\n" +
                        "  FOREIGN KEY (ticker) REFERENCES companies (ticker);",
                "Cоздан внешний ключ analysis_check.ticker -> companies.ticker");
    }
}
