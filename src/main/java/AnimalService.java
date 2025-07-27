import animals.Animal;
import enums.AnimalType;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class AnimalService {
    public void getAll() throws IOException {
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

            System.out.printf("%-3s %-15s %-20s %-15s %-4s %-5s%n", "id", "type", "name", "color", "age", "weight");
            System.out.println("---------------------------------------------------------------------");

            //Выведем данные на экран
            while (result.next()) {

                System.out.printf("%-3s %-15s %-20s %-15s %-4s %-5s%n",
                        result.getInt("id"),
                        result.getString("type"),
                        result.getString("name"),
                        result.getString("color"),
                        result.getInt("age"),
                        result.getInt("weight")

                );
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getAllByType(AnimalType type) throws IOException {
        Properties prop = new Properties();
        InputStream stream = ClassLoader.getSystemResourceAsStream("SQLSettings.properties");
        prop.load(stream);

        String url = prop.getProperty("url");
        String name = prop.getProperty("username");
        String password = prop.getProperty("password");

        String sqlQuery = String.format("SELECT * FROM animal WHERE type = %s", type.getName());

        //Создадим подключение
        try (Connection con = DriverManager.getConnection(url, name, password)) {
            Statement statement = con.createStatement();

            //Выполним запрос
            ResultSet result = statement.executeQuery(sqlQuery);

            System.out.printf("%-3s %-15s %-20s %-15s %-4s %-5s%n", "id", "type", "name", "color", "age", "weight");
            System.out.println("---------------------------------------------------------------------");

            //Выведем данные на экран
            while (result.next()) {

                System.out.printf("%-3s %-15s %-20s %-15s %-4s %-5s%n",
                        result.getInt("id"),
                        result.getString("type"),
                        result.getString("name"),
                        result.getString("color"),
                        result.getInt("age"),
                        result.getInt("weight")

                );
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void save(Animal animal, AnimalType type) throws IOException {
        //Считать конфиг
        Properties prop = new Properties();
        InputStream stream = ClassLoader.getSystemResourceAsStream("SQLSettings.properties");
        prop.load(stream);

        String url = prop.getProperty("url");
        String name = prop.getProperty("username");
        String password = prop.getProperty("password");

        if (animal.getId() == 0) {
            int id = maxId();
            animal.setId(++id);
            String sqlQuery = "INSERT into animal (id, type, name, age, weight, color)VALUES (?, ?, ?, ?, ?, ?)";

            //Создадим подключение
            try (Connection con = DriverManager.getConnection(url, name, password)) {
                PreparedStatement statement = con.prepareStatement(sqlQuery);
                statement.setInt(1, animal.getId());
                statement.setString(2, type.getName());
                statement.setString(3, animal.getName());
                statement.setInt(4, animal.getAge());
                statement.setInt(5, animal.getWeight());
                statement.setString(6, animal.getColor());

                //Выполним запрос
                statement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            String sqlQuery = "UPDATE animal set type = ?, name = ?, age = ?, weight = ?, color = ? WHERE id = ?";
            //Создадим подключение
            try (Connection con = DriverManager.getConnection(url, name, password)) {
                PreparedStatement statement = con.prepareStatement(sqlQuery);
                statement.setString(1, type.getName());
                statement.setString(2, animal.getName());
                statement.setInt(3, animal.getAge());
                statement.setInt(4, animal.getWeight());
                statement.setString(5, animal.getColor());
                statement.setInt(6, animal.getId());

                //Выполним запрос
                statement.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }



    }

    private int maxId() throws IOException {
        Properties prop = new Properties();
        InputStream stream = ClassLoader.getSystemResourceAsStream("SQLSettings.properties");
        prop.load(stream);

        String url = prop.getProperty("url");
        String name = prop.getProperty("username");
        String password = prop.getProperty("password");

        String sqlQuery = "Select * from animal";
        int id = 1;
        //Создадим подключение
        try (Connection con = DriverManager.getConnection(url, name, password)) {
            Statement statement = con.createStatement();

            //Выполним запрос
            ResultSet result = statement.executeQuery(sqlQuery);


            while (result.next()) {
                int currentId = result.getInt("id");
                if (currentId > id) {
                    id = currentId;
                }
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return id;
    }
}


