package todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
  static final String DB_URL = "jdbc:postgresql://localhost/";
  static final String USER = "postgres";
  static final String PASS = "example";

  public static void main(String[] args) {
        System.out.println("Connecting to database...");   	  

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
           Statement stmt = conn.createStatement();
        ) {		      
           String sql = "CREATE DATABASE TODO_DB";
           stmt.executeUpdate(sql);
           System.out.println("Database created successfully...");   	  
        } catch (SQLException e) {
           e.printStackTrace();
        } 
     }
}
