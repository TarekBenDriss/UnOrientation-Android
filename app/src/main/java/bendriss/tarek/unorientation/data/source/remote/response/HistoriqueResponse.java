package bendriss.tarek.unorientation.data.source.remote.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoriqueResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("idUser")
    private int idUser;
    @JsonProperty("resultName")
    private String resultName;
    @JsonProperty("date")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HistoriqueResponse() {
    }

    @Override
    public String toString() {
        return "HistoriqueResponse{" +
                "id='" + id + '\'' +
                ", idUser=" + idUser +
                ", resultName='" + resultName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }
}
