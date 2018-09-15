package com.dev.bruno.learning.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupRepeatedChars {

    private static String groupRepeatedChars(String input) {
        Pattern p = Pattern.compile("((.)\\2*)");
        Matcher m = p.matcher(input);
        StringBuilder groupedString = new StringBuilder();

        while (m.find()) {
            String group = m.group(1);
            groupedString.append(group.charAt(0));
            if(group.length() > 1) groupedString.append(group.length());
        }

        return groupedString.toString();
    }

    public static void main(String [] args) {
        System.out.println(groupRepeatedChars("aaaabbbbbccccsdsa"));
    }
}
