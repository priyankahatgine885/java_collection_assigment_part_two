package com.cognologix.collection.main;

import com.cognologix.collection.model.Candidate;
import com.cognologix.collection.utils.FileHandling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example06 {
    public static void main(String[] args) {
        try {
            Example06 example06 = new Example06();
            List<String> stringList = FileHandling.readFileData("/data/candidateData.txt");
            List<Candidate> candidateList = example06.prepareList(stringList);
            Map<String, List<Candidate>> candidateMap = example06.processData(candidateList);
            Map<String, Float> stringFloatMap = example06.getMaximumVote(candidateMap);
            example06.printEmployeeMap(stringFloatMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Candidate> prepareList(List<String> stringList) {
        List<Candidate> list = new ArrayList<>();
        for (String str : stringList) {
            String[] arrayList = str.split(",", 4);
            Candidate candidate = new Candidate(Integer.parseInt(arrayList[0]), arrayList[1], arrayList[2], Integer.parseInt(arrayList[3].trim()));
            list.add(candidate);
        }
        return list;
    }

    public Map<String, List<Candidate>> processData(List<Candidate> candidateList) {
        Map<String, List<Candidate>> candidateMap = new HashMap<>();
        for (Candidate candidate : candidateList) {
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

    private Map<String, Float> getMaximumVote(Map<String, List<Candidate>> candidateMap) {
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

    public void printEmployeeMap(Map<String, Float> map) {
        for (Map.Entry<String, Float> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " " + "Value: " + entry.getValue());
        }
    }

}
