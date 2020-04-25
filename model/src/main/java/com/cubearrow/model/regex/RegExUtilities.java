package com.cubearrow.model.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtilities {

    /**
     * Returns the starting index of the first substring found in a string with a RegEx expression
     * @param baseString The string that is matched and whose index of the substring is returned
     * @param regexExpression The RegEx expression that is used to match the baseString
     * @param startIndex The index at which the search starts.
     *                   This enables the user to leave out parts before the part to be searched
     * @return Returns the index of the first substring, if there is no substring, returns -1
     */
    public static int getStartingIndexOfFirstSubstring(String baseString, String regexExpression, int startIndex) {
        Pattern pattern = Pattern.compile(regexExpression);
        Matcher matcher = pattern.matcher(baseString);

        if (matcher.find(startIndex)) {
            return matcher.start();
        }
        return -1;
    }
    public static String getFirstSubstring(String baseString, String regexExpression, int startIndex){
        Pattern pattern = Pattern.compile(regexExpression);
        Matcher matcher = pattern.matcher(baseString);

        if (matcher.find(startIndex)) {
            return matcher.group();
        }
        return null;
    }
}