package main;

import model.Candidate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example06 {
    public static void main(String[] args) {
        String[] input = {
                "22, Ravi Pawar, Aundh, 1603",
                "23, Suvarna Kale, Baner, 803",
                "27, Vinod Chavan, Aundh, 809",
                "29, Vasant Mahajan, Aundh, 617",
                "32, Aarti Patil, Baner, 351",
                "34, Swaran Bijur, Baner, 352"

        };
        List<Candidate> candidateList = prepareList(input);
        Map<String, List<Candidate>> candidateMap = processData(candidateList);
        Map<String, Float> stringFloatMap = getMaximumVote(candidateMap);
        printEmployeeMap(stringFloatMap);
    }

    public static List<Candidate> prepareList(String[] input) {
        List<Candidate> list = new ArrayList<>();
        for (String str : input) {
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
