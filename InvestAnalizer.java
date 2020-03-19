import model.Model;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Главный класс программы
public class InvestAnalizer {
    public static void main(String[] args) {
        Model model = null;
        int n = -1;
        List<String> searchList = new ArrayList<>();
        searchList.add("Адрес для парсинга");
        try {
            model = new Model(true);
            for (String searchString : searchList) {
                n++;
                try {
                    model.analyse(searchString);

                } catch (Exception e) {
                    try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("Адрес файла для записи ошибок", true)))) {
                        writer.write("Ссылка "+searchString+". На итерации [" + n + "] возникло исключение: ");
                        e.printStackTrace(writer);
                        writer.write("\n");
                        writer.write("\n");
                    }
                }
            }
        } catch (SQLException | FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
