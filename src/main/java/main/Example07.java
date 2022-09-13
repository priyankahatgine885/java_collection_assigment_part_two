package main;
import model.Candidate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example07 {
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
        printEmployeeMap(candidateMap);
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
                List<Candidate> tempCandidateList= new ArrayList<>();
                tempCandidateList.add(candidate);
                candidateMap.put(constituency, tempCandidateList);
            }
        }
        return candidateMap;
    }
    public static void printEmployeeMap(Map<String, List<Candidate>> candidateMap) {
        for (String key :candidateMap.keySet()) {
            List<Candidate> candidates = candidateMap.get(key);
            System.out.println("Key: " + key + " " + "Value: " + candidates);
        }
    }
}
