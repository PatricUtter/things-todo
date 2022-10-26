package todo;

import com.google.gson.Gson;
import static spark.Spark.*;
import java.util.List;

public class Routes {

  public void setup() {
    Gson gson = new Gson();

    get("/hello", (req, res) -> {
      return "hello world!";
    });

    get("/todos", (req, res) -> {

      var jdbi = DB.getJdbi();

      final List<Todo> todos = jdbi.withHandle(handle -> {
        return handle.createQuery("select * from todo").mapToBean(Todo.class).list();
      });

      res.header("Content/Type", "application/json");

      return new Response("success", gson.toJsonTree(todos));
    }, gson::toJson);
  }
}
