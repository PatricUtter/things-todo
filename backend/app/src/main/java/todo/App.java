package todo;

public class App {


  public static void main(String[] args) {

      // Manually insert an initial state for now
     
      var jdbi = DB.getJdbi();
      jdbi.useHandle(handle -> {
        handle.execute("drop table if exists todo");
        handle.execute("create table todo (id int primary key, status varchar(5), message varchar(255))");
        handle.execute("insert into todo (id, status, message) values (?, ?, ?)", 1, "OPEN", "Do this please");
        handle.execute("insert into todo (id, status, message) values (?, ?, ?)", 2, "OPEN", "And also this please");
        handle.execute("insert into todo (id, status, message) values (?, ?, ?)", 3, "OPEN", "Remember to feed the tigers");
      });

      var routes = new Routes();
      routes.setup();
  }
}
