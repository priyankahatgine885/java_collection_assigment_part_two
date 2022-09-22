package com.cognologix.collection.main;


import com.cognologix.collection.model.Student;
import com.cognologix.collection.utils.FileHandling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example04 {
    public static void main(String[] args) {
        try {
            Example04 example04 = new Example04();
            List<String> stringList = FileHandling.readFileData("/data/studentData.txt");
            Iterable<Student> students = example04.prepareData(stringList);
            Map<Integer, Integer> studentMap = example04.processData(students);
            example04.printStudentMap(studentMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Iterable<Student> prepareData(List<String> stringList) {
        List<Student> students = new ArrayList<>();
        for (String str : stringList) {
            if (!str.isEmpty()) {
                String[] columns = str.split(",");
                Student student = new Student(Integer.parseInt(columns[0]), columns[1], Integer.parseInt(columns[2].trim()));
                students.add(student);
            }
        }
        return students;
    }


    private Map<Integer, Integer> processData(Iterable<Student> students) {
        Map<Integer, Integer> studentMap = new HashMap<>();
        int lowestId = Integer.MAX_VALUE;
        int averageMark = 0;
        int sum = 0;
        int count = 0;
        for (Student student : students) {
            int id = student.getId();
            if (id <= lowestId) {
                lowestId = id;
                sum += student.getMark();
                ++count;
            }
        }
        if (count != 0) {
            averageMark = sum / count;
        }
        studentMap.put(lowestId, averageMark);
        return studentMap;
    }

    public void printStudentMap(Map<Integer, Integer> studentMap) {
        for (Map.Entry<Integer, Integer> entry : studentMap.entrySet()) {
            Integer key = entry.getKey();
            Integer students = entry.getValue();
            System.out.println("Key: " + key + " " + "Value: " + students);
        }
    }
}
