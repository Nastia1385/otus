import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        //Считать конфиг
        Properties prop = new Properties();
        InputStream stream = ClassLoader.getSystemResourceAsStream("SQLSettings.properties");
        prop.load(stream);

        String url = prop.getProperty("url");
        String name = prop.getProperty("username");
        String password = prop.getProperty("password");

        String sqlQuery = "Select * from animal";


        //Создадим подключение
        try (Connection con = DriverManager.getConnection(url, name, password)) {
            Statement statement = con.createStatement();

            //Выполним запрос
            ResultSet result = statement.executeQuery(sqlQuery);

            System.out.printf("%-3s %-15s %-20s %-15s %-4s %-5s%n" , "id", "color", "name", "type", "age", "weight");
            System.out.println("---------------------------------------------------------------------");

            //Выведем данные на экран
            while (result.next()) {

                System.out.printf("%-3s %-15s %-20s %-15s %-4s %-5s%n",
                        result.getInt("id"),
                        result.getString("color"),
                        result.getString("name"),
                        result.getString("type"),
                        result.getInt("age"),
                        result.getInt("weight")

                );
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

