/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author Kang Wei
 */
public class IdGenerator {
    private static int getLastId(Path filePath) throws IOException { // get last id for id generate
        List<String> lines = Files.readAllLines(filePath);
        if (lines.isEmpty()) {
            System.out.println("no record exist");
            return 0;
        }
        String lastLine = lines.get(lines.size() - 1);
        String[] data = lastLine.split("\\|");
        return Integer.parseInt(data[0]);
    }

    //Use By zw
    public static Long getNewId(Path filePath) throws IOException {
        return Long.valueOf(getLastId(filePath) + 1);
    }

}
