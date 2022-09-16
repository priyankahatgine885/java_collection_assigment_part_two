package main;

import model.Employee;
import utils.FileHandling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example05 {
    public static void main(String[] args) {
        List<String> stringList;
        Example05 example = new Example05();
        try {
            stringList = FileHandling.readFileData("/data/EmployeeData.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Employee> employeeList = Example05.prepareList(stringList);
        Map<String, List<Employee>> employeeMap = Example05.processData(employeeList);
        Map<String, Employee> stringEmployeeMap = Example05.getHighestSalary(employeeMap);
        Example05.printEmployeeMap(stringEmployeeMap);
    }

    private static void printEmployeeMap(Map<String, Employee> employeeMap) {
        for (Map.Entry<String, Employee> entry : employeeMap.entrySet()) {
            Employee employee = entry.getValue();
            System.out.println("Key: " + entry.getKey() + " " + "Value: " + employee);
        }
    }

    private static List<Employee> prepareList(List<String> stringList) {
        List<Employee> list = new ArrayList<>();
        for (String str : stringList) {
            String[] columns = str.split(",", 4);
            Employee employee = new Employee(Integer.parseInt(columns[0]), columns[1], columns[2], Float.parseFloat(columns[3].trim()));
            list.add(employee);
        }
        return list;
    }

    private static Map<String, List<Employee>> processData(List<Employee> employeeList) {
        Map<String, List<Employee>> employeeMap = new HashMap<>();
        for (Employee employee : employeeList) {
            String departmentOfEmp = employee.getDepartment();
            if (employeeMap.containsKey(departmentOfEmp)) {
                List<Employee> empList = employeeMap.get(departmentOfEmp);
                empList.add(employee);
            } else {
                List<Employee> tempEmployeeList = new ArrayList<>();
                tempEmployeeList.add(employee);
                employeeMap.put(departmentOfEmp, tempEmployeeList);
            }
        }
        return employeeMap;
    }

    private static Map<String, Employee> getHighestSalary(Map<String, List<Employee>> employeeMap) {
        Map<String, Employee> stringEmployeeMap = new HashMap<>();
        float maxValue = 0.0f;
        for (Map.Entry<String, List<Employee>> entry : employeeMap.entrySet()) {
            List<Employee> employeeList = entry.getValue();
            for (Employee employee : employeeList) {
                if (employee.getSalary() > maxValue) {
                    maxValue = employee.getSalary();
                    stringEmployeeMap.put(entry.getKey(), employee);
                }
            }
            maxValue = 0.0f;
        }
        return stringEmployeeMap;
    }
}
