package com.cognologix.collection.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Student {
    private int id;

    private String subject;

    private int mark;

    @Override
    public String toString() {
        return String.format("%s %s %s", id, subject, mark);
    }

}
