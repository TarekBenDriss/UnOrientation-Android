package bendriss.tarek.unorientation.modules.jobstats;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.TypeConverters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import bendriss.tarek.unorientation.data.source.local.converter.Converters;


@TypeConverters(Converters.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Scrap implements Cloneable, Parcelable {

    @JsonProperty("nbrTunisie")
    private String nbrTunisie;
    @JsonProperty("nbrMonde")
    private String nbrMonde;
    @JsonProperty("resultName")
    private String resultName;
    @JsonProperty("jobs")
    private List<Job> jobs;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Scrap() {
    }


    public static Creator<Scrap> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "Scrap{" +
                "nbrTunisie='" + nbrTunisie + '\'' +
                ", nbrMonde='" + nbrMonde + '\'' +
                ", resultName='" + resultName + '\'' +
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

    public Scrap(String nbrTunisie, String nbrMonde, String resultName) {
        this.nbrTunisie = nbrTunisie;
        this.nbrMonde = nbrMonde;
        this.resultName = resultName;
    }

    public static final Creator<Scrap> CREATOR = new Creator<Scrap>() {


        @Override
        public Scrap createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Scrap[] newArray(int size) {
            return new Scrap[0];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }



}

