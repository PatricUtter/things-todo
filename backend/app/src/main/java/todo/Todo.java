package todo;

import java.io.Serializable;

public class Todo implements Serializable {
  private int id;
  private String message;
  private String status;

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
}
