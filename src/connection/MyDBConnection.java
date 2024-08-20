package connection;

import constant.Constant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDBConnection {

    // This is the Singleton instance
    private static MyDBConnection instance = null;
    private Connection connection;

    private MyDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(Constant.dburl, Constant.userdbname, Constant.userdbpass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Thread-safe Singleton implementation using synchronized
    public static synchronized MyDBConnection getInstance() {
        if (instance == null) {
            instance = new MyDBConnection();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}
