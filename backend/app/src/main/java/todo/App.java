package todo;

import static spark.Spark.*;

import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.List;
import java.sql.Connection;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;

//import com.google.gson.Gson;

public class App {

  static final String DB_URL = "jdbc:postgresql://localhost/todo_db";
  static final String USER = "postgres";
  static final String PASS = "example";

  public static void main(String[] args) {

    //Gson gson = new Gson();

    System.out.println("Connecting to the database...");
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
      Jdbi jdbi = Jdbi.create(conn)
          .installPlugin(new PostgresPlugin());

      jdbi.useHandle(handle -> {
        handle.execute("create table if not exists todo (id int primary key, status varchar(5), message varchar(255))");
        handle.execute("truncate table todo");
        handle.execute("insert into todo (id, status, message) values (?, ?, ?)", 1, "OPEN", "Do this please");
        handle.execute("insert into todo (id, status, message) values (?, ?, ?)", 2, "OPEN", "And also this please");
        handle.execute("insert into todo (id, status, message) values (?, ?, ?)", 3, "OPEN", "Remember to feed the tigers");
      });

      get("/hello", (req, res) -> {
        return "hello world!";
      });

    } catch (SQLException e) {
      System.out.println(e);

    }
  }
}
