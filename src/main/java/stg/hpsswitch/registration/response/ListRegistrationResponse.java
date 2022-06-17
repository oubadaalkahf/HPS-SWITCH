package stg.hpsswitch.registration.response;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"data",
"message",
"status"
})
@Generated("jsonschema2pojo")
public class ListRegistrationResponse {

@JsonProperty("data")
private List<Data> data = null;
@JsonProperty("message")
private String message;
@JsonProperty("status")
private Integer status;

@JsonProperty("data")
public List<Data> getData() {
return data;
}

@JsonProperty("data")
public void setData(List<Data> data) {
this.data = data;
}

@JsonProperty("message")
public String getMessage() {
return message;
}

@JsonProperty("message")
public void setMessage(String message) {
this.message = message;
}

@JsonProperty("status")
public Integer getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(Integer status) {
this.status = status;
}

}