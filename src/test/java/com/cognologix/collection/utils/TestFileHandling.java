package com.cognologix.collection.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class TestFileHandling {
    @Test
    void testReadFileData() {
        List<String> stringList;
        try {
            stringList = FileHandling.readFileData("/data/softwareData.txt");
            Assertions.assertEquals(6, stringList.size());
            for (String str : stringList) {
                Assertions.assertNotEquals(null, str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
