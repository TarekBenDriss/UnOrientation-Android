package bendriss.tarek.unorientation.data.source.remote.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import bendriss.tarek.unorientation.data.source.local.entity.UserProfile;

/**
 * this class represents the login response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

    @JsonProperty("type")
    private String type;
    @JsonProperty("token")
    private String token;
    @JsonProperty("user")
    private UserProfile user;
    @JsonProperty("_id")
    private String _id;
    @JsonProperty("updated")
    private String updated;

    public String get__v() {
        return type;
    }

    public void set__v(String __v) {
        this.type = __v;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginPokResponse{" +
                "__v='" + type + '\'' +
                ", token='" + token + '\'' +
                ", user='" + user + '\'' +
                ", _id='" + _id + '\'' +
                ", updated=" + updated +
                '}';
    }
}
