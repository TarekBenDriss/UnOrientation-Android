package bendriss.tarek.unorientation.data.source.remote.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import bendriss.tarek.unorientation.modules.jobstats.Job;



@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScrapResponse {


    @JsonProperty("nbrTunisie")
    private String nbrTunisie;
    @JsonProperty("nbrMonde")
    private String nbrMonde;
    @JsonProperty("resultName")
    private String resultName;
    @JsonProperty("jobs")
    private List<Job> jobs;

    @Override
    public String toString() {
        return "ScrapResponse{" +
                "nbrTunisie='" + nbrTunisie + '\'' +
                ", nbrMonde='" + nbrMonde + '\'' +
                ", resultName='" + resultName + '\'' +
                ", jobs=" + jobs +
                '}';
    }

    public String getNbrTunisie() {
        return nbrTunisie;
    }

    public void setNbrTunisie(String nbrTunisie) {
        this.nbrTunisie = nbrTunisie;
    }

    public String getNbrMonde() {
        return nbrMonde;
    }

    public void setNbrMonde(String nbrMonde) {
        this.nbrMonde = nbrMonde;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public ScrapResponse() {
    }

    public ScrapResponse(String nbrTunisie, String nbrMonde, String resultName, List<Job> jobs) {
        this.nbrTunisie = nbrTunisie;
        this.nbrMonde = nbrMonde;
        this.resultName = resultName;
        this.jobs = jobs;
    }
}