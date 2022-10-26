package todo;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;

public class DB {
  static final String DB_URL = "jdbc:postgresql://localhost/todo_db";
  static final String USER = "postgres";
  static final String PASS = "example";

  public static Jdbi getJdbi() {
    Jdbi jdbi = null;
    try {
      Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
      jdbi = Jdbi.create(conn)
          .installPlugin(new PostgresPlugin());

    } catch (SQLException e) {
      System.out.println(e);
    }

    return jdbi;
  }
}
