package com.cognologix.collection.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SoftwareStatus {
    private String latestVersion;
    private Integer installedCount;

    public String toString() {
        return String.format("%s %s", latestVersion, installedCount);
    }
}