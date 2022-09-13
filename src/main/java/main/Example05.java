package main;

import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example05 {
    public static void main(String[] args) {
        String[] input = {
                "22, Data Structures, 45",
                "23, English, 52",
                "22, English, 51",
                "26, Data Structures, 72",
                "23, Data Structures, 61",
                "24, English, 81"
        };
        Iterable<Student> students = prepareData(input);
        Map<Integer, Integer> studentMap = processData(students);
        printStudentMap(studentMap);
    }

    private static Iterable<Student> prepareData(String[] input) {
        List<Student> students = new ArrayList<>();
        for (String str : input) {
            String[] arrayList = str.split(",");
            Student student = new Student(Integer.parseInt(arrayList[0]), arrayList[1], Integer.parseInt(arrayList[2].trim()));
            students.add(student);
        }
        return students;
    }

    private static Map<Integer, Integer> processData(Iterable<Student> students) {
        Map<Integer, Integer> studentMap = new HashMap<>();
        int lowestId = Integer.MAX_VALUE;
        int averageMark = 0;
        int sum = 0;
        int count = 0;
        for (Student student : students) {
            int id = student.getId();
            if (id <= lowestId) {
                lowestId = id;
                if (id == lowestId) {
                    sum += student.getMark();
                    ++count;
                }
            }
        }
        averageMark = sum / count;
        studentMap.put(lowestId, averageMark);
        return studentMap;
    }

    public static void printStudentMap(Map<Integer, Integer> studentMap) {
        for (Map.Entry<Integer, Integer> entry : studentMap.entrySet()) {
            Integer key = entry.getKey();
            Integer students = entry.getValue();
            System.out.println("Key: " + key + " " + "Value: " + students);
        }
    }
}
