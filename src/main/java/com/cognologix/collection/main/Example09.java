package com.cognologix.collection.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Example09 {
    public static void main(String[] args) {
        Example09 example = new Example09();
        final String stringFirst = "listen";
        final String stringSecond = "enlists";
        if (Example09.isAnagram(stringFirst, stringSecond)) {
            System.out.println(stringFirst + " and " + stringSecond + " are Anagrams");
        } else {
            System.out.println(stringFirst + " and " + stringSecond + " are NOT Anagrams");
        }
    }

    private static boolean isAnagram(String stringFirst, String stringSecond) {
        stringFirst = stringFirst.toLowerCase();
        stringSecond = stringSecond.toLowerCase();

        if (stringFirst.length() != stringSecond.length())
            return false;

        List<Character> list1 = new ArrayList<>();
        List<Character> list2 = new ArrayList<>();

        for (int i = 0; i < stringFirst.length(); i++) {
            list1.add(stringFirst.charAt(i));
        }
        for (int i = 0; i < stringSecond.length(); i++) {
            list2.add(stringSecond.charAt(i));
        }

        Collections.sort(list1);
        Collections.sort(list2);

        if (list1.equals(list2))
            return true;
        else
            return false;
    }
}
