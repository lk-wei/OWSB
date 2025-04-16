/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kang Wei
 */
public class IdGenerator {

    Path filePath;

    public IdGenerator(String filePath) {
        this.filePath = Path.of(filePath);
    }

    public IdGenerator(Path filePath) {
        this.filePath = filePath;
    }

    public Long getId() {
        Long newId = null;
        try {
            newId = Long.valueOf(getLastId(filePath) + 1);
        } catch (IOException ex) {
            Logger.getLogger(IdGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newId;
    }

    private String getLastId() throws IOException { // get last id for id generate
        List<String> lines = Files.readAllLines(filePath);
        if (lines.isEmpty()) {
            System.out.println("no record exist");
            return 0;
        }
        String lastLine = lines.get(lines.size() - 1);
        String[] data = lastLine.split("\\|");
        return data[0];
    }

    //Use By zw
    public Long getNewId() {
        Long newId = null;
        try {
            String lastId = getLastId();
            if (lastId != null) {
                newId = Long.parseLong(lastId) + 1;
            } else {
            }
        } catch (IOException ex) {
            Logger.getLogger(IdGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newId;
    }

}
