package bendriss.tarek.unorientation.modules.jobstats;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.TypeConverters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import bendriss.tarek.unorientation.data.source.local.converter.Converters;

@TypeConverters(Converters.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Job implements Cloneable, Parcelable {
    @JsonProperty("name")
    private String name;
    @JsonProperty("company")
    private String company;
    @JsonProperty("location")
    private String location;

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static Creator<Job> getCREATOR() {
        return CREATOR;
    }

    public Job(String name, String company, String location) {
        this.name = name;
        this.company = company;
        this.location = location;
    }

    public Job() {
    }

    protected Job(Parcel in) {
        name = in.readString();
        company = in.readString();
        location = in.readString();
    }

    public static final Creator<Job> CREATOR = new Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(company);
        dest.writeString(location);
    }
}
