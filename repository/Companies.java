package model.repository;

import java.sql.SQLException;

//Запись данных о компании в базу данных
public class Companies extends BaseTable implements TableOperations {
    public Companies() throws SQLException {
        super("companies");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS companies\n" +
                "(\n" +
                "  ticker varchar(15) NOT NULL COMMENT 'Обозначение акций компании на бирже',\n" +
                "  company_name text COMMENT 'Название компании',\n" +
                "  industry text COMMENT 'Отрасль компании',\n" +
                "  CONSTRAINT pk_companies PRIMARY KEY (ticker)\n" +
                ");", "Создана таблица " + tableName);
    }


    @Override
    public void createForeignKeys() throws SQLException {

    }
}
