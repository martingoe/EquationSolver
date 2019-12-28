package model.regex;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtilities {

    HashMap<String, Integer> getSubstrings(String baseString, String regExString) {
        HashMap<String, Integer> result = new HashMap<>();
        Pattern pattern = Pattern.compile(regExString);
        Matcher matcher = pattern.matcher(baseString);

        while (matcher.find()) {
            result.put(matcher.group(), matcher.start());
        }

        return result.size() == 0 ? null : result;
    }
}
