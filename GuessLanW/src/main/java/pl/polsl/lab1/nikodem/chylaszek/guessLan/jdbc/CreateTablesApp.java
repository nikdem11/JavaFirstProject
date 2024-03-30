package pl.polsl.lab1.nikodem.chylaszek.guessLan.jdbc;

import java.sql.*;

/**
 * Creates tabels for database.
 * 
 * @author Nikodem
 * @version 1.0
 */
public class CreateTablesApp {

    /**
     *
     */
    public void createTables() {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found");
        }
        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab5", "app5", "app5")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE Languages "
                    + "(id_lan INTEGER, language VARCHAR(50), PRIMARY KEY(id_lan))");
            statement.executeUpdate("CREATE TABLE Questions "
                    + "(id_q INTEGER PRIMARY KEY, question VARCHAR(100), hint VARCHAR(100), id_lan INTEGER, "
                    + "FOREIGN KEY(id_lan) REFERENCES Languages(id_lan))"); 
            statement.executeUpdate("CREATE TABLE Results "
                    + "(name VARCHAR(50), score VARCHAR(50))");
            statement.executeUpdate("CREATE TABLE ActivityLog "
                    + "(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY , name VARCHAR(50), action VARCHAR(255), " 
                    + "timestamp TIMESTAMP, details VARCHAR(255))");
            
            System.out.println("Table created");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        CreateTablesApp createTablesApp = new CreateTablesApp();
        createTablesApp.createTables();
    }
}

