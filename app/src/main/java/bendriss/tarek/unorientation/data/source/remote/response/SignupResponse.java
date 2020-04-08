package bendriss.tarek.unorientation.data.source.remote.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import bendriss.tarek.unorientation.data.source.local.entity.UserProfile;

/**
 * Created by Tarek Ben Driss on 21/03/2020.
 */



@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignupResponse {

    @JsonProperty("user")
    private UserProfile user;
    @JsonProperty("status")
    private String status;

    public SignupResponse() {
    }

    public SignupResponse(UserProfile user, String status) {
        this.user = user;
        this.status = status;
    }

    @Override
    public String toString() {
        return "SignupResponse{" +
                "user=" + user +
                ", status='" + status + '\'' +
                '}';
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}