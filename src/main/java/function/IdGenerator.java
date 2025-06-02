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

    private static int getLastId(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        // Search backwards for the last non-empty line
        for (int i = lines.size() - 1; i >= 0; i--) {
            String line = lines.get(i).trim();
            if (!line.isEmpty()) {
                String[] data = line.split("\\|");
                return Integer.parseInt(data[0]);
            }
        }

        System.out.println("No record exists");
        return 0;
    }
    
    //Use By zw
    public static Long getNewId(Path filePath) throws IOException {
        return Long.valueOf(getLastId(filePath) + 1);
    }
}
