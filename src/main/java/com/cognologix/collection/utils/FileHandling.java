package com.cognologix.collection.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileHandling {

    public static List<String> readFileData(String filePath) throws IOException {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader lineReader = new BufferedReader(new FileReader(
                Paths.get(Objects.requireNonNull(FileHandling.class
                        .getResource(filePath)).getPath()).toFile()
        ))) {
            String lineText;
            while (!Objects.isNull(lineText = lineReader.readLine()) && lineText.length() != 0) {
                stringList.add(lineText);
            }
        }
        return stringList;
    }


}
