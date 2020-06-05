package bendriss.tarek.unorientation.util;

import java.util.Collection;
import java.util.Map;

/**
 * this class represents the collections utils
 */
public class CollectionUtils {

    /**
     * this function checks if map is empty
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Map collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * this function returns the size of a map
     * @param collection
     * @return
     */
    public static int size(Map collection) {
        return isNotEmpty(collection) ? collection.size() : 0;
    }

    /**
     * this function checks if a collection is not empty
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * this function returns the size of a collection
     * @param collection
     * @return
     */
    public static int size(Collection collection) {
        return isNotEmpty(collection) ? collection.size() : 0;
    }

    /**
     * this function returns the size of many collections
     * @param collections
     * @return
     */
    public static int sizes(Collection... collections) {
        int sizes = 0;
        if (collections != null) {
            for (Collection collection : collections) {
                sizes += size(collection);
            }
        }
        return sizes;
    }

    /**
     * this function checks if an array is not empty
     * @param array
     * @param <T>
     * @return
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return array != null && array.length > 0;
    }

    /**
     * this function returns the array's size
     * @param array
     * @param <T>
     * @return
     */
    public static <T> int size(T[] array) {
        return isNotEmpty(array) ? array.length : 0;
    }

    /**
     * this function returns a string from char sequence
     * @param charSequence
     * @return
     */
    public static String charSequenceToString(CharSequence charSequence) {
        return charSequence != null ? charSequence.toString() : "";
    }

    /**
     * this function returns a string from char sequence
     * @param array
     * @return
     */
    public static String[] charSequenceToString(CharSequence[] array) {
        String[] strings = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            strings[i] = charSequenceToString(array[i]);
        }
        return strings;
    }
}
