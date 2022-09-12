package main;

import model.Software;
import model.SoftwareStatus;
import java.util.*;

public class Example01 {
    public static void main(String[] args) {

        String[] data = {
                "Server2, Database, MySQL, 5.1",
                "Server3, OS, Ubuntu, 12.04",
                "Server1, OS, Ubuntu, 12.04",
                "Server2, OS, Ubuntu, 18.04",
                "Server3, Language, Python, 2.6.3",
                "Server1, Database, MySQL, 5.5"
        };

        List<Software> softWaresList = prepareData(Arrays.asList(data));
        Map<String, SoftwareStatus> softwareMap = getLatestSoftwareTypeNameVersionMap(softWaresList);
        printSoftwareMap(softwareMap);
        System.out.println("-----------------------------");
        List<Software> outDatedSoftware = getOutOfDateVersionIsInstalledOnAtLeastTwoDifferentServers(softWaresList, softwareMap);
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
                System.out.println(columns[3]);
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
