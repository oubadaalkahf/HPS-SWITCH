package stg.hpsswitch.registration.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({
"email",
"firstName",
"lastName",
"fcm_token",
"appUserRole",
"locked",
"enabled",
"username",
"authorities",
"accountNonExpired",
"accountNonLocked",
"credentialsNonExpired"
})
@Generated("jsonschema2pojo")
public class Data {

@JsonProperty("email")
private String email;
@JsonProperty("firstName")
private String firstName;
@JsonProperty("lastName")
private String lastName;
@JsonProperty("fcm_token")
private Object fcmToken;
@JsonProperty("appUserRole")
private String appUserRole;
@JsonProperty("locked")
private Boolean locked;
@JsonProperty("enabled")
private Boolean enabled;
@JsonProperty("username")
private String username;
@JsonProperty("authorities")
private List<Authority> authorities = null;
@JsonProperty("accountNonExpired")
private Boolean accountNonExpired;
@JsonProperty("accountNonLocked")
private Boolean accountNonLocked;
@JsonProperty("credentialsNonExpired")
private Boolean credentialsNonExpired;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("email")
public String getEmail() {
return email;
}

@JsonProperty("email")
public void setEmail(String email) {
this.email = email;
}

@JsonProperty("firstName")
public String getFirstName() {
return firstName;
}

@JsonProperty("firstName")
public void setFirstName(String firstName) {
this.firstName = firstName;
}

@JsonProperty("lastName")
public String getLastName() {
return lastName;
}

@JsonProperty("lastName")
public void setLastName(String lastName) {
this.lastName = lastName;
}

@JsonProperty("fcm_token")
public Object getFcmToken() {
return fcmToken;
}

@JsonProperty("fcm_token")
public void setFcmToken(Object fcmToken) {
this.fcmToken = fcmToken;
}

@JsonProperty("appUserRole")
public String getAppUserRole() {
return appUserRole;
}

@JsonProperty("appUserRole")
public void setAppUserRole(String appUserRole) {
this.appUserRole = appUserRole;
}

@JsonProperty("locked")
public Boolean getLocked() {
return locked;
}

@JsonProperty("locked")
public void setLocked(Boolean locked) {
this.locked = locked;
}

@JsonProperty("enabled")
public Boolean getEnabled() {
return enabled;
}

@JsonProperty("enabled")
public void setEnabled(Boolean enabled) {
this.enabled = enabled;
}

@JsonProperty("username")
public String getUsername() {
return username;
}

@JsonProperty("username")
public void setUsername(String username) {
this.username = username;
}

@JsonProperty("authorities")
public List<Authority> getAuthorities() {
return authorities;
}

@JsonProperty("authorities")
public void setAuthorities(List<Authority> authorities) {
this.authorities = authorities;
}

@JsonProperty("accountNonExpired")
public Boolean getAccountNonExpired() {
return accountNonExpired;
}

@JsonProperty("accountNonExpired")
public void setAccountNonExpired(Boolean accountNonExpired) {
this.accountNonExpired = accountNonExpired;
}

@JsonProperty("accountNonLocked")
public Boolean getAccountNonLocked() {
return accountNonLocked;
}

@JsonProperty("accountNonLocked")
public void setAccountNonLocked(Boolean accountNonLocked) {
this.accountNonLocked = accountNonLocked;
}

@JsonProperty("credentialsNonExpired")
public Boolean getCredentialsNonExpired() {
return credentialsNonExpired;
}

@JsonProperty("credentialsNonExpired")
public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
this.credentialsNonExpired = credentialsNonExpired;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}