package bendriss.tarek.unorientation.data.source.local.entity;



import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * this class represents the user profile's model
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class UserProfile {

    @NonNull
    @PrimaryKey
    private int id;
    @JsonProperty("culture")
    private String culture;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("userKey")
    private String userKey;
    @JsonProperty("email")
    private String email;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("offset")
    private String offset;

    @JsonProperty("organizationId")
    private String organizationId;

    @JsonProperty("profil")
    private String profil;

    @JsonProperty("type")
    private String type;
    @JsonProperty("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", culture='" + culture + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userKey='" + userKey + '\'' +
                ", email='" + email + '\'' +
                ", timezone='" + timezone + '\'' +
                ", offset='" + offset + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", profil='" + profil + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserProfile(@NonNull int id, String culture, String firstName, String lastName, String userKey, String email, String timezone, String offset, String organizationId, String profil, String type) {

        this.id = id;
        this.culture = culture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userKey = userKey;
        this.email = email;
        this.timezone = timezone;
        this.offset = offset;
        this.organizationId = organizationId;
        this.profil = profil;
        this.type = type;
    }

    public UserProfile() {

    }
}
