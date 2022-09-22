package com.cognologix.collection.main;

public class Example07 {
    public static void main(String[] args) {
        Example07 example07 = new Example07();
        int[] firstArray = {1, 2, 3,};
        int[] secondArray = {1, 2, 3, 4, 5};
        if (example07.isEqual(firstArray, secondArray)) {
            System.out.println("First array is equal to Second array ");
        } else if (example07.isSubSet(firstArray, secondArray)) {
            System.out.println("First array is a subset of Second array ");
        } else if (example07.isSuperSet(firstArray, secondArray)) {
            System.out.println("First array is a superset of Second array ");
        } else {
            System.out.println("First array is not a superset of, subset of or not equal to Second array \n");
        }
    }

    private boolean isSubSet(int[] firstArray, int[] secArray) {
        int j;
        if (firstArray.length > secArray.length) {
            return false;
        } else {
            for (int k : firstArray) {
                for (j = 0; j < secArray.length; j++) {
                    if (k == secArray[j])
                        break;
                }
                if (j == secArray.length)
                    return false;
            }
        }
        return true;
    }

    private boolean isSuperSet(int[] firstArray, int[] secArray) {
        int j;
        if (firstArray.length < secArray.length) {
            return false;
        } else {
            for (int k : secArray) {
                for (j = 0; j < firstArray.length; j++) {
                    if (firstArray[j] == k)
                        break;
                }
                if (j == secArray.length)
                    return false;
            }
        }
        return true;
    }

    private boolean isEqual(int[] firstArray, int[] secArray) {
        if (firstArray.length != secArray.length) {
            return false;
        } else {
            int j;
            for (int k : firstArray) {
                for (j = 0; j < secArray.length; j++) {
                    if (secArray[j] == k)
                        break;
                }
                if (j == firstArray.length)
                    return false;
            }
        }
        return true;
    }
}
