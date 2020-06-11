package bendriss.tarek.unorientation.modules.history;

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
public class History implements Cloneable, Parcelable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("idUser")
    private int idUser;
    @JsonProperty("resultName")
    private String resultName;
    @JsonProperty("date")
    private String date;

    @Override
    public String toString() {
        return "History{" +
                "id='" + id + '\'' +
                ", idUser=" + idUser +
                ", resultName='" + resultName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public History() {
    }

    public History(String id, int idUser, String resultName) {
        this.id = id;
        this.idUser = idUser;
        this.resultName = resultName;
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

    public static Creator<History> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





    public static final Creator<History> CREATOR = new Creator<History>() {


        @Override
        public History createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public History[] newArray(int size) {
            return new History[0];
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

