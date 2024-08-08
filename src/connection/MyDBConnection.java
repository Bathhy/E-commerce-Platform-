package connection;

import constant.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyDBConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(Constant.dburl, Constant.userdbname, Constant.userdbpass);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
