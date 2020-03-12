package bendriss.tarek.unorientation.data.source.local.converter;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * this class represents a custom json date deserializer
 */

public class CustomJsonDateDeserializer extends JsonDeserializer<Date>
{
    /**
     * this function deserialize a date json to a date
     * @param jsonparser
     * @param deserializationcontext
     * @return
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public Date deserialize(JsonParser jsonparser,
                            DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
        DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
        String dateStr = jsonparser.getText();
        try {
            return (Date)formatter.parse(dateStr);
        } catch (ParseException e) {
            try {
                return (Date)formatter2.parse(dateStr);
            } catch (ParseException e1) {
                e1.printStackTrace();
                throw new RuntimeException(e);
            }
            //throw new RuntimeException(e);
        }
    }


}