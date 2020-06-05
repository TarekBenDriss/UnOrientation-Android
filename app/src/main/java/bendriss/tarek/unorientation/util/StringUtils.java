package bendriss.tarek.unorientation.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;



import java.net.URLDecoder;

import bendriss.tarek.unorientation.R;

/**
 * this class represents string utils
 */
public class StringUtils {

    public static final String UTF8 = "UTF-8";

    /**
     * this function returns the month abreviation from a number
     * @param i
     * @return
     */
    public static String getMonthFromNbr(int i)
    {
        Log.e("MONTH"," "+i);
        String s ="";
        switch (i) {
            case 1:
                s= "JAN";
                break;
            case 2:
                s= "FEV";
                break;
            case 3:
                s= "MAR";
                break;
            case 4:
                s= "AVR";
                break;
            case 5:
                s= "MAI";
                break;
            case 6:
                s= "JUN";
                break;
            case 7:
                s= "JUL";
                break;
            case 8:
                s= "AOU";
                break;
            case 9:
                s= "SEP";
                break;
            case 10:
                s= "OCT";
                break;
            case 11:
                s= "NOV";
                break;
            case 12:
                s= "DEC";
                break;
        }
        return s;
    }

    /**
     * this function returns the month name from a number
     * @param context
     * @param i
     * @return
     */
    public static String getMonthFromNbr2(Context context, int i)
    {
        Log.e("MONTH"," "+i);
        String s ="";
        switch (i) {
            case 1:
                s= context.getResources().getString(R.string.m1);
                break;
            case 2:
                s= context.getResources().getString(R.string.m2);
                break;
            case 3:
                s= context.getResources().getString(R.string.m3);
                break;
            case 4:
                s= context.getResources().getString(R.string.m4);
                break;
            case 5:
                s= context.getResources().getString(R.string.m5);
                break;
            case 6:
                s= context.getResources().getString(R.string.m6);
                break;
            case 7:
                s= context.getResources().getString(R.string.m7);
                break;
            case 8:
                s= context.getResources().getString(R.string.m8);
                break;
            case 9:
                s= context.getResources().getString(R.string.m9);
                break;
            case 10:
                s= context.getResources().getString(R.string.m10);
                break;
            case 11:
                s= context.getResources().getString(R.string.m11);
                break;
            case 12:
                s= context.getResources().getString(R.string.m12);
                break;
        }
        return s;
    }

    /**
     * this function returns the short month from number
     * @param context
     * @param i
     * @return
     */
    public static String getShortMonthFromNbr(Context context, int i)
    {
        String s ="";
        switch (i) {
            case 1:
                s= context.getResources().getString(R.string.sm1);
                break;
            case 2:
                s= context.getResources().getString(R.string.sm2);
                break;
            case 3:
                s= context.getResources().getString(R.string.sm3);
                break;
            case 4:
                s= context.getResources().getString(R.string.sm4);
                break;
            case 5:
                s= context.getResources().getString(R.string.sm5);
                break;
            case 6:
                s= context.getResources().getString(R.string.sm6);
                break;
            case 7:
                s= context.getResources().getString(R.string.sm7);
                break;
            case 8:
                s= context.getResources().getString(R.string.sm8);
                break;
            case 9:
                s= context.getResources().getString(R.string.sm9);
                break;
            case 10:
                s= context.getResources().getString(R.string.sm10);
                break;
            case 11:
                s= context.getResources().getString(R.string.sm11);
                break;
            case 12:
                s= context.getResources().getString(R.string.sm12);
                break;
        }
        return s;
    }

    /**
     * this function returns the day of the week
     * @param i
     * @return
     */
    public static String getDayFromWeek(int i)
    {
        Log.e("DAY_OF_WEEK"," "+i);
        String s ="";
        switch (i) {
            case 1:
                s= "DIM";
                break;
            case 2:
                s= "LUN";
                break;
            case 3:
                s= "MAR";
                break;
            case 4:
                s= "MER";
                break;
            case 5:
                s= "JEU";
                break;
            case 6:
                s= "VEN";
                break;
            case 7:
                s= "SAM";
                break;
        }
        return s;
    }

    /**
     * this function returns if the string is alphabetic or not
     * @param aString
     * @return
     */
    public static boolean isAlphabetic(String aString){
        int charCount=0;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        if(aString.length() == 0) return false;//zero length string ain't alpha
        for(int i=0;i<aString.length();i++){
            for(int j=0;j<alphabet.length();j++){
                if(aString.substring(i,i+1).equals(alphabet.substring(j,j+1))
                        || aString.substring(i,i+1).equals(alphabet.substring(j,j+1).toLowerCase()))
                    charCount++;
            }
            if(charCount != (i+1)){
                System.out.println("\n**Invalid input! Enter alpha values**\n");
                return false;
            }
        }
        return true;
    }

    /**
     * this function returns if string is email or not
     * @param email
     * @return
     */
    public static Boolean isEmail(String email) {
        return email.contains("@") && email.contains(".") && email.indexOf("@")!=email.length() && email.indexOf("@")!=0
                && email.indexOf("@")!=email.length()-1  && email.indexOf("@")!=email.length()-2  && email.indexOf(".")!=0
                && email.indexOf(".")!=email.length() && email.indexOf(".")!=email.length()-1
                && email.indexOf("@")!=email.length()-3 && email.indexOf(".")!=email.length()-2;
    }

    /**
     * this function decodes the url
     * @param str
     * @return
     */
    public static String decodeURL(String str) {
        String decoded = str;
        if (isNotEmpty(str)) {
            try {
                decoded = URLDecoder.decode(str, UTF8);
            } catch (Exception e) {
                decoded = str;
            }
        }
        return decoded;
    }

    /**
     * this function decodes utf8
     * @param str
     * @return
     */
    public static String decodeUTF8(String str) {
        String decoded = str;
        if (isNotEmpty(str)) {
            byte[] src = str.getBytes();
            try {
                decoded = decodeURL(new String(src, UTF8));
            } catch (Exception e) {
                decoded = str;
            }
        }
        return decoded;
    }

    /**
     * this function returns a string with the first char is upper
     * @param str
     * @return
     */
    public static String upperFirstChar(String str) {
        return isNotEmpty(str) ? str.substring(0, 1).toUpperCase() + str.substring(1) : str;
    }

    /**
     * this function reutrn the uppered stirng
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        return isNotEmpty(str) ? str.toUpperCase() : str;
    }

    /**
     * this function return the lowered string
     * @param str
     * @return
     */
    public static String lowerCase(String str) {
        return isNotEmpty(str) ? str.toLowerCase() : str;
    }

    /**
     * this function checks if string is empty or not
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    public static boolean isLength(CharSequence str, int i) {
        return str.length()==i;
    }

    /**
     * this function checks if two strings are equals
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        return TextUtils.equals(a, b);
    }

    /**
     * this function checks if char sequence is not empty
     * @param str
     * @return
     */
    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    /**
     * this function trims a string
     * @param str
     * @return
     */
    public static String trim(String str) {
        return (isNotEmpty(str)) ? str.trim().replaceAll("\\s+", "") : str;
    }

    /**
     * this function trims and remplaces a string
     * @param str
     * @return
     */
    public static String trimAndReplace(String str) {
        return (isNotEmpty(str)) ? trim(str).replace("/", "") : str;
    }
}
