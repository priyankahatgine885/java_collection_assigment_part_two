package com.cognologix.collection.main;

public class Example07 {
    public static void main(String[] args) {
        Example07 example = new Example07();
        int[] firstArray = {1, 2, 3,};
        int[] secondArray = {1, 2, 3, 4, 5};
        if (Example07.isEqual(firstArray, secondArray)) {
            System.out.println("First array is equal to Second array ");
        } else if (Example07.isSublist(firstArray, secondArray)) {
            System.out.println("First array is a sublist of Second array ");
        } else if (Example07.isSuperlist(firstArray, secondArray)) {
            System.out.println("First array is a superlist of Second array ");
        } else {
            System.out.println("First array is not a superlist of, sublist of or equal to Second array\n");
        }
    }

    private static boolean isSublist(int[] firstArray, int[] secArray) {
        int j;
        if (firstArray.length > secArray.length) {
            return false;
        } else {
            for (int i = 0; i < firstArray.length; i++) {
                for (j = 0; j < secArray.length; j++) {
                    if (firstArray[i] == secArray[j])
                        break;
                }
                if (j == secArray.length)
                    return false;
            }
        }
        return true;
    }

    private static boolean isSuperlist(int[] firstArray, int[] secArray) {
        int j;
        if (firstArray.length < secArray.length) {
            return false;
        } else {
            for (int i = 0; i < secArray.length; i++) {
                for (j = 0; j < firstArray.length; j++) {
                    if (firstArray[j] == secArray[i])
                        break;
                }
                if (j == secArray.length)
                    return false;
            }
        }
        return true;
    }

    private static boolean isEqual(int[] firstArray, int[] secArray) {
        if (firstArray.length != secArray.length) {
            return false;
        } else {
            int j;
            for (int i = 0; i < firstArray.length; i++) {
                for (j = 0; j < secArray.length; j++) {
                    if (secArray[j] == firstArray[i])
                        break;
                }
                if (j == firstArray.length)
                    return false;
            }
        }
        return true;
    }
}
