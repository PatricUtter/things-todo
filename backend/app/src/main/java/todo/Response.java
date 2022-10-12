package todo;

import com.google.gson.JsonElement;

public class Response {
  private String status;
  private String message;
  private JsonElement data;

  public Response (String status) {
    this.status = status;
  }

  public Response (String status, String message) {
    this.status = status;
    this.status = message;
  }

  public Response (String status, JsonElement data) {
    this.status = status;
    this.data = data;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public JsonElement getData() {
    return data;
  }

  public void setData(JsonElement data) {
    this.data = data;
  }

}

