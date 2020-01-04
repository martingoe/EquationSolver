package model.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtilities {

    public static Integer getFirstSubstring(String baseString, String regExString, int startIndex) {
        Pattern pattern = Pattern.compile(regExString);
        Matcher matcher = pattern.matcher(baseString);

        if (matcher.find(startIndex)) {
            return matcher.start();
        }
        return null;
    }
}
