package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ReadFile {
    private final File file;

    public ReadFile(File file) {
        this.file = file;
    }

    public synchronized String getContent(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (InputStream inputStream = new FileInputStream(file)) {
            int data;
            while ((data = inputStream.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append(data);
                }
            }
            return output.toString();
        }
    }
}