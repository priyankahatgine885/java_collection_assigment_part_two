package com.cognologix.collection.main;

import model.Candidate;
import utils.FileHandling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example06 {
    public static void main(String[] args) {
        Example06 example = new Example06();
        List<String> stringList = null;
        try {
            stringList = FileHandling.readFileData("/data/CandidateData.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Candidate> candidateList = Example06.prepareList(stringList);
        Map<String, List<Candidate>> candidateMap = Example06.processData(candidateList);
        Map<String, Float> stringFloatMap = Example06.getMaximumVote(candidateMap);
        Example06.printEmployeeMap(stringFloatMap);
    }

    public static List<Candidate> prepareList(List<String> stringList) {
        List<Candidate> list = new ArrayList<>();
        for (String str : stringList) {
            String[] arrayList = str.split(",", 4);
            Candidate candidate = new Candidate(Integer.parseInt(arrayList[0]), arrayList[1], arrayList[2], Integer.parseInt(arrayList[3].trim()));
            list.add(candidate);
        }
        return list;
    }

    public static Map<String, List<Candidate>> processData(List<Candidate> candidateList) {
        Map<String, List<Candidate>> candidateMap = new HashMap<>();
        for (int i = 0; i < candidateList.size(); ++i) {
            Candidate candidate = candidateList.get(i);
            String constituency = candidate.getConstituency();
            if (candidateMap.containsKey(constituency)) {
                List<Candidate> empList = candidateMap.get(constituency);
                empList.add(candidate);
            } else {
                List<Candidate> tempCandidateList = new ArrayList<>();
                tempCandidateList.add(candidate);
                candidateMap.put(constituency, tempCandidateList);
            }
        }
        return candidateMap;
    }

    private static Map<String, Float> getMaximumVote(Map<String, List<Candidate>> candidateMap) {
        Map<String, Float> stringEmployeeMap = new HashMap<>();
        float maxValue = 0.0f;
        for (Map.Entry<String, List<Candidate>> entry : candidateMap.entrySet()) {
            List<Candidate> candidateList = entry.getValue();
            for (Candidate candidate : candidateList) {
                if (candidate.getVotes() > maxValue) {
                    maxValue = candidate.getVotes();
                    stringEmployeeMap.put(entry.getKey(), maxValue);
                }
            }
            maxValue = 0.0f;
        }
        return stringEmployeeMap;
    }

    public static void printEmployeeMap(Map<String, Float> map) {
        for (String constituency : map.keySet()) {
            Float vote = map.get(constituency);
            System.out.println("Key: " + constituency + " " + "Value: " + vote);
        }
    }
}
