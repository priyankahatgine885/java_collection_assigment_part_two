package com.cognologix.collection.main;

import com.cognologix.collection.model.Software;
import com.cognologix.collection.model.SoftwareStatus;
import com.cognologix.collection.utils.FileHandling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Out of Date Software :
Consider the file input.txt which contains all information about software installed on various servers in a data center:

Server1, Database, MySQL, 5.5
Server2, Database, MySQL, 5.1
Server3, OS, Ubuntu, 12.04
Server1, OS, Ubuntu, 12.04
Server2, OS, Ubuntu, 18.04
Server3, Language, Python, 2.6.3

This file indicates that Server1 has version 5.5 of MySQL installed, and Server2 has version 5.1 installed, and Server3 has version 12.04 of Ubuntu installed. For the purposes of this program, assume that all version numbers are of the form X.Y or X.Y.Z where X, Y, and Z are made up of only digits.

Write a program that reads this file (input.txt in the current directory), and prints to file output.txt  (in the current directory) a list of software package names for which an out-of-date version  (i.e. a version which is not the latest version) is installed on at least 2 different servers.
Thus, in this case, the output of your program should be:
Ubuntu
Because Ubuntu 12.04 is an out-of-date version (the latest version is 18.04), and it is installed on two servers (Server 3, and Server 1).
 */
public class Example01 {
    public static void main(String[] args) {
        List<String> stringList = null;
        Example01 example = new Example01();
        try {
            stringList = FileHandling.readFileData("/data/SoftwareData.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Software> softWaresList = Example01.prepareData(stringList);
        Map<String, SoftwareStatus> softwareMap = Example01.getLatestSoftwareTypeNameVersionMap(softWaresList);
        Example01.printSoftwareMap(softwareMap);
        System.out.println("-----------------------------");
        List<Software> outDatedSoftware = Example01.getOutOfDateVersionIsInstalledOnAtLeastTwoDifferentServers(softWaresList, softwareMap);
        System.out.println("Outdated Software is : ");
        for (Software software : outDatedSoftware) {
            System.out.println(software);
        }
    }

    private static List<Software> prepareData(List<String> stringList) {
        List<Software> softWares = new ArrayList<>();
        for (String str : stringList) {
            if (!str.isEmpty()) {
                String[] columns = str.split(",");
                Software softWare = new Software(columns[0], columns[1], columns[2], columns[3]);
                softWares.add(softWare);
            }
        }
        return softWares;
    }

    private static Map<String, SoftwareStatus> getLatestSoftwareTypeNameVersionMap(Iterable<Software> softWares) {
        Map<String, SoftwareStatus> softwareMap = new HashMap<>();
        for (Software softWare : softWares) {
            String softWareTypename = softWare.getSoftwareTypeName();
            String currentVersion = softWare.getVersionNumber();
            if (softwareMap.containsKey(softWareTypename)) {
                SoftwareStatus softwareStatus = softwareMap.get(softWareTypename);
                int SoftwareCount = softwareStatus.getInstalledCount();
                SoftwareCount++;
                softwareStatus.setInstalledCount(SoftwareCount);
                String latestVersion = softwareStatus.getLatestVersion();
                if (currentVersion.compareTo(latestVersion) > 0) {
                    latestVersion = currentVersion;
                    softwareStatus.setLatestVersion(latestVersion);
                    softwareMap.put(softWareTypename, softwareStatus);
                }
            } else {
                SoftwareStatus softwareStatus = new SoftwareStatus(currentVersion, 1);
                softwareMap.put(softWareTypename, softwareStatus);
            }
        }
        return softwareMap;
    }

    private static List<Software> getOutOfDateVersionIsInstalledOnAtLeastTwoDifferentServers(Iterable<Software> softWares, Map<String, SoftwareStatus> latestSoftwareVersion) {
        List<Software> softWareList = new ArrayList<>();
        for (Software softWare : softWares) {
            String softwareTypeName = softWare.getSoftwareTypeName();
            String currentVersion = softWare.getVersionNumber();
            SoftwareStatus softwareStatus = latestSoftwareVersion.get(softwareTypeName);
            String softwareStatusLatestVersion = softwareStatus.getLatestVersion();
            int softwareCount = softwareStatus.getInstalledCount();
            if (softwareCount > 2) {
                if (currentVersion.compareTo(softwareStatusLatestVersion) < 0) {
                    softWareList.add(softWare);
                }
            }
        }
        return softWareList;
    }

    public static void printSoftwareMap(Map<String, SoftwareStatus> softwareMap) {
        for (Map.Entry<String, SoftwareStatus> entry : softwareMap.entrySet()) {
            String key = entry.getKey();
            SoftwareStatus version = entry.getValue();
            System.out.println("Key: " + key + " " + "Value: " + version);
        }
    }
}
