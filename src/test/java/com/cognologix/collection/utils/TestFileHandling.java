package com.cognologix.collection.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TestFileHandling {

    @Test
    public void testReadFileData() {
        List<String> stringList = null;
        try {
            stringList = FileHandling.readFileData("/data/SoftwareData.txt");
            Assertions.assertEquals(6, stringList.size());
            for (String str : stringList) {
                Assertions.assertNotEquals(null, str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
