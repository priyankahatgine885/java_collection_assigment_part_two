package com.cognologix.collection.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;

    private String name;

    private String department;

    private Float salary;
}
