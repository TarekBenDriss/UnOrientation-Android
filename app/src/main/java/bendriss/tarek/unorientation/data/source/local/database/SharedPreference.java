package bendriss.tarek.unorientation.data.source.local.database;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import bendriss.tarek.unorientation.util.ApplicationUtils;
import bendriss.tarek.unorientation.util.StringUtils;

/**
 * this class implements a custom shared preferences
 */
public class SharedPreference {

    private final static String NAME = "SharedPreferences";
    private final SharedPreferences sharedPreferences;
    private final ObjectMapper objectMapper;

    public SharedPreference(@NonNull Context context, @NonNull ObjectMapper objectMapper) {
        this.sharedPreferences = context.getSharedPreferences(ApplicationUtils.NAME + NAME, Context.MODE_PRIVATE);
        this.objectMapper = objectMapper;
    }

    /**
     * this function puts a boolean
     * @param key
     * @param value
     */
    public void putBoolean(@NonNull String key, boolean value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    /**
     * this function puts a float
     * @param key
     * @param value
     */
    public void putFloat(@NonNull String key, float value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putFloat(key, value);
        edit.apply();
    }

    /**
     * this function puts an int
     * @param key
     * @param value
     */
    public void putInt(@NonNull String key, int value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    /**
     * this function puts a long
     * @param key
     * @param value
     */
    public void putLong(@NonNull String key, long value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(key, value);
        edit.apply();
    }

    /**
     * this function puts a double
     * @param key
     * @param value
     */
    public void putDouble(@NonNull String key, double value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, String.valueOf(value));
        edit.apply();
    }

    /**
     * this function puts a string
     * @param key
     * @param value
     */
    public void putString(@NonNull String key, String value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.apply();
    }

    /**
     * this function puts an object
     * @param key
     * @param object
     * @param <T>
     */
    public <T> void putObject(@NonNull String key, T object) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String value = convertToString(object);
        edit.putString(key, value);
        edit.apply();
    }

    /**
     * this function puts a stringset
     * @param key
     * @param value
     */
    public void putStringSet(@NonNull String key, Set<String> value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putStringSet(key, value);
        edit.apply();
    }

    /**
     * this function returns the shared preferences
     * @return
     */
    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    /**
     * this function returns a boolean from a key
     * @param key
     * @param defValue
     * @return
     */
    public boolean getBoolean(@NonNull String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    /**
     * this function returns a float from a key
     * @param key
     * @param defValue
     * @return
     */
    public float getFloat(@NonNull String key, float defValue) {
        return sharedPreferences.getFloat(key, defValue);
    }

    /**
     * this function returns a double from a key
     * @param key
     * @param defValue
     * @return
     */
    public double getDouble(@NonNull String key, double defValue) {
        String valueStr = sharedPreferences.getString(key, String.valueOf(defValue));
        return StringUtils.isNotEmpty(valueStr) ? Double.parseDouble(valueStr) : 0.0;
    }

    /**
     * this function returns an int from a key
     * @param key
     * @param defValue
     * @return
     */
    public int getInt(@NonNull String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    /**
     * this function returns a long from a keyu
     * @param key
     * @param defValue
     * @return
     */
    public long getLong(@NonNull String key, long defValue) {
        return sharedPreferences.getLong(key, defValue);
    }

    /**
     * this function returns a string from a key
     * @param key
     * @param defValue
     * @return
     */
    public String getString(@NonNull String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }

    /**
     * this function returns a string set
     * @param key
     * @param defValue
     * @return
     */
    public Set<String> getStringSet(@NonNull String key, Set<String> defValue) {
        return sharedPreferences.getStringSet(key, defValue);
    }

    /**
     * this function gets an object from a key
     * @param key
     * @param valueType
     * @param <T>
     * @return
     */
    public <T> T getObject(@NonNull String key, Class<T> valueType) {
        final String str = sharedPreferences.getString(key, null);
        return StringUtils.isNotEmpty(str) ? convertToObject(str, valueType) : null;
    }

    /**
     * this function checks if the shared preferences contains a key
     * @param key
     * @return
     */
    public boolean contains(@NonNull String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * this function delets a key
     * @param key
     */
    public void remove(@NonNull String key) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(key);
        edit.apply();
    }

    /**
     * this function clear all the shared preferences
     */
    public void clearAll() {
        Map<String, ?> preferences = sharedPreferences.getAll();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        for (Map.Entry<String, ?> me : preferences.entrySet()) {
            String key = me.getKey();
            edit.remove(key);
        }
        edit.apply();
    }

    /**
     * this function converts a string to an object
     * @param src
     * @param valueType
     * @param <T>
     * @return
     */
    private <T> T convertToObject(String src, Class<T> valueType) {
        T data;
        try {
            data = objectMapper.readValue(src, valueType);
        } catch (IOException error) {
            data = null;
        }
        return data;
    }

    /**
     * this function converts an object to a string
     * @param value
     * @param <T>
     * @return
     */
    private <T> String convertToString(T value) {
        String decoded;
        if (value != null) {
            try {
                decoded = objectMapper.writeValueAsString(value);
            } catch (IOException e) {
                decoded = null;
            }
        } else {
            decoded = null;
        }
        return decoded;
    }
}
