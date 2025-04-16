/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import function.IdGenerator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kang Wei
 */
public abstract class MasterRepo<T> {
    protected final Path filePath;

    public MasterRepo(Path filePath) {
        this.filePath = filePath;
    }

    // Common CRUD operations
    public void create(T entity) throws IOException {
        
        setId(entity, IdGenerator.getNewId(filePath));
        List<String> lines = Files.readAllLines(filePath);
        lines.add(objectToString(entity));
        Files.write(filePath, lines);
    }

    public List<T> getAll() throws IOException {
        List<T> list = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            T entity = stringToObject(line);
            list.add(entity);
        }
        return list;
    }

    public T getById(Long id) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            T entity = stringToObject(line);

            if (getId(entity).equals(id)) {
                return entity;
            }
        }
        return null;
    }

    public void update(T entity) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            T e = stringToObject(line);

            if (getId(e).equals(getId(entity))) {
                updatedLines.add(objectToString(entity));
            } else {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    public void delete(T entity) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            T e = stringToObject(line);

            if (!getId(e).equals(getId(entity))) {
                updatedLines.add(line);
            }
        }
        Files.write(filePath, updatedLines);
    }

    // to be implemented by child classes
    protected abstract Long getId(T entity);
    protected abstract void setId(T entity, long id);
    protected abstract String objectToString(T entity); // For Storage to TXT file
    protected abstract T stringToObject(String line);   // Convert lines form TXT files to objects
}
