package com.lfp.py_lite_compiler.utils;

import java.io.*;
import java.util.stream.Collectors;

public class FileManager {
    public String getFileContent(File file) {
        StringBuilder content = new StringBuilder();
        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(fileInputStream));
                String line;
                while ((line = buffer.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content.toString();
    }

}
