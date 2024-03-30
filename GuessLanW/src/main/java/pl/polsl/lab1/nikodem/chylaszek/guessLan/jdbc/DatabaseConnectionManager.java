package pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages database connections.
 * 
 * @author Nikodem
 * @version 1.0
 */
public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;
    private String url = "jdbc:derby://localhost:1527/lab5";
    private String username = "app5";
    private String password = "app5";

    private DatabaseConnectionManager() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    /**
     * Get the database connection instance.
     *
     * @return The database connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Get the singleton instance of the DatabaseConnectionManager.
     *
     * @return The DatabaseConnectionManager instance.
     * @throws SQLException If there is an error in database connection.
     */
    public static DatabaseConnectionManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnectionManager();
        }

        return instance;
    }
}
