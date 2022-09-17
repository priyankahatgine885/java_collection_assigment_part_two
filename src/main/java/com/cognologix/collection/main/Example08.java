package com.cognologix.collection.main;

import java.util.HashSet;
import java.util.Set;

public class Example08 {
    public static void main(String[] args) {
        Example08 example = new Example08();
        final String givenWord = "Machine";
        boolean result = Example08.isIsogram(givenWord);
        if (result) {
            System.out.println(givenWord + " is an isogram");
        } else {
            System.out.println(givenWord + " is not isogram");
        }
    }

    private static boolean isIsogram(String word) {
        Set<Character> set = new HashSet<>();
        word = word.toLowerCase();
        char[] arr = word.toCharArray();
        for (char ch : arr) {
            set.add(ch);
        }
        if (set.size() == word.length())
            return true;
        else
            return false;
    }
}
