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
        return handle.select("select * from todo").mapToBean(Todo.class).list();
      });

      res.header("Content/Type", "application/json");

      return new Response("success", gson.toJsonTree(todos));
    }, gson::toJson);

    get("/todo/:id", (req, res) -> {

      var jdbi = DB.getJdbi();

      final Todo todo = jdbi.withHandle(handle -> {
        int id = Integer.parseInt(req.params("id"));

        return handle.select("select * from todo where id = :id").bind("id", id).mapToBean(Todo.class).one(); //Fixme: this should use findOne and handle Optional
      });

      if (todo == null) {
        return new Response("error", "Todo with id " + req.params("id") + " does not exist");

      }

      return new Response("success", gson.toJsonTree(todo));

    }, gson::toJson);

    post("/todo", (req, res) -> {

      var jdbi = DB.getJdbi();

      var todo = gson.fromJson(req.body(), Todo.class);

      jdbi.useHandle(handle -> {
        handle.execute("insert into todo (status, message) values (?, ?)", "OPEN", todo.getMessage());
      });

      return new Response("success", "TODO created");

    }, gson::toJson);
  }

}
