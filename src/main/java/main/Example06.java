package main;

import model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example06 {
    public static void main(String[] args) {
        String[] input = {
                "22, Rajan Anand, Engineering, 1600000",
                "23, Swati Patil, Testing, 800000",
                "27, Vijay Chawda, Engineering, 800000",
                "29, Basant Mahapatra, Engineering, 600000",
                "32, Ajay Patel, Testing, 350000",
                "34, Swaraj Birla, Testing, 350000"
        };
        List<Employee> employeeList = prepareList(input);
        Map<String, List<Employee>> employeeMap = processData(employeeList);
        Map<String, Employee> stringEmployeeMap = getHighestSalary(employeeMap);
        printEmployeeMap(stringEmployeeMap);
    }

    private static void printEmployeeMap(Map<String, Employee> employeeMap) {
        for (Map.Entry<String, Employee> entry : employeeMap.entrySet()) {
            Employee employee = entry.getValue();
            System.out.println("Key: " + entry.getKey() + " " + "Value: " + employee);
        }
    }

    private static List<Employee> prepareList(String[] input) {
        List<Employee> list = new ArrayList<>();
        for (String str : input) {
            String[] arrayList = str.split(",", 4);
            Employee employee = new Employee(Integer.parseInt(arrayList[0]), arrayList[1], arrayList[2], Integer.parseInt(arrayList[3].trim()));
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
