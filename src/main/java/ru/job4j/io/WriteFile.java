package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class WriteFile {
    private final File file;

    public WriteFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            o.write(content.getBytes());
        }
    }
}