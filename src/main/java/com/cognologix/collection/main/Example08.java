package com.cognologix.collection.main;

import java.util.HashSet;
import java.util.Set;

public class Example08 {
    public static void main(String[] args) {
        Example08 example08 = new Example08();
        final String givenWord = "Machine";
        boolean result = example08.isIsogram(givenWord);
        if (result) {
            System.out.println(givenWord + " is an isogram");
        } else {
            System.out.println(givenWord + " is not isogram");
        }
    }

    private boolean isIsogram(String word) {
        Set<Character> set = new HashSet<>();
        word = word.toLowerCase();
        char[] arr = word.toCharArray();
        for (char ch : arr) {
            set.add(ch);
        }
        return (set.size() == word.length());
    }
}
