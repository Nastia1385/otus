package connector;


import settings.PropertiesSettings;

import javax.imageio.IIOException;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

public class MySqlDbConnector implements IDBConnector{

    private Map <String, String> dbSettings = null;

    private static Connection connection = null;

    private static Statement statement = null;

    public MySqlDbConnector() throws SQLException, IOException {
        dbSettings = new PropertiesSettings().getSettings("db.properties");
        connect();
    }

    private void connect() throws SQLException {
        if (connection ==null) {
            connection = DriverManager.getConnection(
                    this.dbSettings.get("url"),
                    this.dbSettings.get("username"),
                    this.dbSettings.get("password")
            );
        }

        if (statement == null) {
            statement = connection.createStatement();
        }
    }

    private void close() throws SQLException {
        if(statement !=null) {
            statement.close();
        }

        if (connection !=null) {
            connection.close();
        }
    }

    public void execute (String sqlRequest) throws SQLException {
        statement.execute(sqlRequest);
    }

    public ResultSet executeQuery(String sqlRequest) throws SQLException {
        return statement.executeQuery(sqlRequest);

    }

}
