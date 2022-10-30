package todo;

import com.google.gson.Gson;
import static spark.Spark.*;
import java.util.List;

public class Routes {

  public void setup() {
    Gson gson = new Gson();

    // GETs
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

    //POSTs
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
        handle.createUpdate("insert into todo (status, message) values (:status, :message)").bind("status", "OPEN").bind("message", todo.getMessage()).execute();
      });

      return new Response("success", "TODO created");

    }, gson::toJson);

    //PUTs
    put("/todo/:id", (req, res) -> {
      var jdbi = DB.getJdbi();
      var todo = gson.fromJson(req.body(), Todo.class);

      int id = Integer.parseInt(req.params("id"));

      jdbi.useHandle(handle -> {
        handle.createUpdate("update todo set status = :status, message = :message where id = :id").bind("status", todo.getStatus()).bind("message", todo.getMessage()).bind("id", id).execute();
      });

      return new Response("success", "TODO updated");
    }, gson::toJson);
    
    //DELETEs
    delete("/todo/:id", (req, res) -> {
      var jdbi = DB.getJdbi();

      int id = Integer.parseInt(req.params("id"));

      jdbi.useHandle(handle -> {
        handle.createUpdate("delete from todo where id = :id").bind("id", id).execute();
      });

      return new Response("success", "TODO deleted");
    }, gson::toJson);
  }



}
