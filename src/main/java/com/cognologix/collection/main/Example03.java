package com.cognologix.collection.main;


import com.cognologix.collection.model.Product;
import com.cognologix.collection.utils.FileHandling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example03 {
    public static void main(String[] args) {
        try {
            Example03 example03 = new Example03();
            List<String> stringList = FileHandling.readFileData("/data/inputData.txt");
            List<Product> productList = example03.prepareData(stringList);
            Map<String, String> employeeMap = example03.getLatestLibraryVersionMap(productList);
            System.out.println("Latest library Version is : ");
            example03.printProductMap(employeeMap);
            System.out.println("-----------------------------");
            List<Product> outDatedLibraryProducts = example03.getOutdatedLibraryProducts(productList, employeeMap);
            System.out.println("OutDated library Version is : ");
            for (Product productName : outDatedLibraryProducts) {
                System.out.println(productName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Product> prepareData(List<String> stringList) {
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

    private Map<String, String> getLatestLibraryVersionMap(Iterable<Product> products) {
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

    private List<Product> getOutdatedLibraryProducts(Iterable<Product> products, Map<String, String> latestLibraryVersionMap) {
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

    public void printProductMap(Map<String, String> productMap) {
        for (Map.Entry<String, String> entry : productMap.entrySet()) {
            String key = entry.getKey();
            String product = entry.getValue();
            System.out.println("Key: " + key + " " + "Value: " + product);
        }
    }
}
