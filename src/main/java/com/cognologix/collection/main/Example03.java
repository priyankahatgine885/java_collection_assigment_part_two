package com.cognologix.collection.main;

import model.Product;
import utils.FileHandling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example03 {
    public static void main(String[] args) {
        List<String> stringList;
        Example03 example = new Example03();
        try {
            stringList = FileHandling.readFileData("/data/InputData.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Product> productList = Example03.prepareData(stringList);
        Map<String, String> employeeMap = Example03.getLatestLibraryVersionMap(productList);
        System.out.println("Latest library Version is : ");
        Example03.printProductMap(employeeMap);
        System.out.println("-----------------------------");
        List<Product> outDatedLibraryProducts = Example03.getOutdatedLibraryProducts(productList, employeeMap);
        System.out.println("OutDated library Version is : ");
        for (Product productName : outDatedLibraryProducts) {
            System.out.println(productName);
        }
    }

    private static List<Product> prepareData(List<String> stringList) {
        List<Product> products = new ArrayList<>();
        for (String str : stringList) {
            if (!str.isEmpty()) {
                String[] columns = str.split(",");
                Product product = new Product(columns[0], columns[1], columns[2]);
                products.add(product);
            }
        }
        return products;
    }

    private static Map<String, String> getLatestLibraryVersionMap(Iterable<Product> products) {
        Map<String, String> productMap = new HashMap<>();
        for (Product product : products) {
            String library = product.getLibraryName();
            String currentVersion = product.getVersion();
            if (productMap.containsKey(library)) {
                String latestVersion = productMap.get(library);
                if (latestVersion.compareTo(currentVersion) > 0) {
                    currentVersion = latestVersion;
                }
            }
            productMap.put(library, currentVersion);
        }
        return productMap;
    }

    private static List<Product> getOutdatedLibraryProducts(Iterable<Product> products, Map<String, String> latestLibraryVersionMap) {
        List<Product> list = new ArrayList<>();
        for (Product product : products) {
            String library = product.getLibraryName();
            String currentVersion = product.getVersion();
            String latestVersion = latestLibraryVersionMap.get(library);
            if (currentVersion.compareTo(latestVersion) < 0) {
                list.add(product);
            }
        }
        return list;
    }

    public static void printProductMap(Map<String, String> productMap) {
        for (Map.Entry<String, String> entry : productMap.entrySet()) {
            String key = entry.getKey();
            String product = entry.getValue();
            System.out.println("Key: " + key + " " + "Value: " + product);
        }
    }
}