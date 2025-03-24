/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kang Wei
 */
public class CustomFile {
    private final String fileName;
    private final List<String> targetLines = new ArrayList<>();
    private List<String> fileLines = new ArrayList<>();
    private List<String> updatedLines;
    private FileWriter fileWriter;

    public CustomFile(String fileName) {
        this.fileName = fileName;
    }

    public void readFile() {
        try {
            fileLines = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public List<String> getFile() {
        readFile();
        return fileLines;
    }

    public String[] getLine(int index, String identifier) { // Retrieves one line return array
        readFile();
        for (String line : fileLines) {
            String[] data = line.split("=");
            if (data[index].equals(identifier)) {
                return data;
            }
        }
        return null;
    }

    public List<String> getLines(int index, String identifier) { // Retrieves multiple lines
        targetLines.clear();
        readFile();
        for (String line : fileLines) {
            String[] data = line.split("=");
            if (data[index].equals(identifier)) {
                targetLines.add(line);
            }
        }
        return targetLines;
    }

    public void appendToFile(String data) { //add data to file
        try {
            fileWriter = new FileWriter(fileName, true);
            fileWriter.write(data);
            fileWriter.write("\n"); // next data start on new line
            System.out.println("Data appended successfully.");
        } catch (IOException e) {
            System.err.println("IOException caught: " + e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.err.println("IOException caught while closing FileWriter: " + e.getMessage());
                }
            }
        }
    }

    public String generateId() { //auto create id based on last line id
        String lastId = getLastId();
        if (lastId == null) {
            return null;
        }
        String prefix = lastId.substring(0, 3);
        int number = Integer.parseInt(lastId.substring(3)) + 1;

        String nextId = String.format("%s%03d", prefix, number);
        return nextId;
    }

    private String getLastId() { // get last id for id generate
        readFile();
        if (fileLines.isEmpty()) {
            System.out.println("File does not exist");
            return null;
        }
        String lastLine = fileLines.get(fileLines.size() - 1);
        String[] data = lastLine.split("=");
        return data[0];
    }

    public void updateFile(String updatedLine) { // file update
        readFile();
        updatedLines = new ArrayList<>();
        String[] updatedData = updatedLine.split("=");
        for (String line : fileLines) {
            String[] data = line.split("=");
            if (updatedData[0].equals(data[0])) {
                updatedLines.add(updatedLine);
            } else {
                updatedLines.add(line);
            }
        }

        try {
            fileWriter = new FileWriter(fileName, false);
            for (String line : updatedLines) {
                fileWriter.write(line + "\n");
            }
            System.out.println("Data appended successfully.");
        } catch (IOException e) {
            System.err.println("IOException caught: " + e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.err.println("IOException caught while closing FileWriter: " + e.getMessage());
                }
            }
        }
    }

    public void deleteLine(String dataId, int index) {
        readFile();
        updatedLines = new ArrayList<>();
        for (String line : fileLines) {
            String[] data = line.split("=");
            if (!dataId.equals(data[index])) {
                updatedLines.add(line);
            }
        }
        try {
            fileWriter = new FileWriter(fileName, false);
            for (String line : updatedLines) {
                fileWriter.write(line + "\n");
            }
            System.out.println("Data deketed successfully.");
        } catch (IOException e) {
            System.err.println("IOException caught: " + e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.err.println("IOException caught while closing FileWriter: " + e.getMessage());
                }
            }
        }
    }
}
