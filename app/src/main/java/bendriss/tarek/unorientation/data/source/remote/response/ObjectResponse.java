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
public class ObjectResponse {

    @JsonProperty("responseMessage")
    private String responseMessage;
    @JsonProperty("responseError")
    private String responseError;

    @Override
    public String toString() {
        return "ObjectResponse{" +
                "responseMessage='" + responseMessage + '\'' +
                ", responseError='" + responseError + '\'' +
                '}';
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseError() {
        return responseError;
    }

    public void setResponseError(String responseError) {
        this.responseError = responseError;
    }

    public ObjectResponse() {
    }

    public ObjectResponse(String responseMessage, String responseError) {
        this.responseMessage = responseMessage;
        this.responseError = responseError;
    }
}
