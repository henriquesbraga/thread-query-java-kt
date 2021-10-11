package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    public static Connection getConnection() {
        Connection conn = null;
        final String DRIVER = "org.postgresql.Driver";
        final String URL = "jdbc:postgresql://192.168.1.120/postgres?currentSchema=henrique_northwind";
        final String USER = "henrique";
        final String PASSWORD = "1234";

        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
