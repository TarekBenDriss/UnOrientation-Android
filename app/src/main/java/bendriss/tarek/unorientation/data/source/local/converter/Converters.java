package bendriss.tarek.unorientation.data.source.local.converter;

import android.util.Log;

import androidx.room.TypeConverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * this class provides oonverters for many types
 */
public class Converters {

    /**
     * this function converts a timestamp to date
     * @param timestamp
     * @return
     */
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    /**
     * this function converts a string to a date
     * @param dateStr
     * @return
     */
    @TypeConverter
    public static Date stringToDate(String dateStr)
    {
        Date date=null;
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            date = (Date)formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("el date",date+"");
        return date;
    }

    /**
     * this function converts a date to a timestamp
     * @param date
     * @return
     */
    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    /**
     * this function converts a string to a list
     * @param str
     * @return
     */
    @TypeConverter
    public static List<Long> toList(String str) {
        List<Long> list;
        try {
            list = new ObjectMapper().readValue(str, new TypeReference<List<Long>>() {
            });
        } catch (IOException error) {
            list = null;
        }
        return list;
    }

    /**
     * this function converts a list to a string
     * @param list
     * @return
     */
    @TypeConverter
    public static String toString(List<Long> list) {
        String decoded;
        if (list != null) {
            try {
                decoded = new ObjectMapper().writeValueAsString(list);
            } catch (IOException e) {
                decoded = null;
            }
        } else {
            decoded = null;
        }
        return decoded;
    }



   
}
