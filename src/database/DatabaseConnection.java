package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private static Connection connection;

    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies_datamining", "root", "");
        }
        catch (Exception e) {
            System.out.println("Database connection error");
            System.out.println("Check internet connection");
        }
    }

    public static Statement getStatement(){
        System.out.println(2);
        if (instance == null){
            instance = new DatabaseConnection();
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
        }
        catch (Exception e) {
            System.out.println("Statement error");
        }
        return statement;
    }

    public static Connection getConnection(){
        if (instance == null){
            instance = new DatabaseConnection();
        }
        return connection;
    }
}
