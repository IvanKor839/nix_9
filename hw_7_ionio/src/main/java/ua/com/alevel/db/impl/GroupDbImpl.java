package ua.com.alevel.db.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ua.com.alevel.db.GroupDb;
import ua.com.alevel.entity.Group;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.UUID;

public class GroupDbImpl implements GroupDb {

    private static final String PATH_JSON = "hw_7_ionio/groups.json";
    private final File file = new File(PATH_JSON);
    private Collection<Group> groups;
    private static GroupDbImpl instance;

    public static GroupDbImpl getInstance() {
        if (instance == null) {
            instance = new GroupDbImpl();
        }
        return instance;
    }

    public GroupDbImpl() {
        try {
            file.createNewFile();
            groups = findAll();
            if (groups == null) {
                groups = new LinkedHashSet<>();
            }
        } catch (IOException e) {
            System.out.println("problem: " + e.getMessage());
        }
    }

    @Override
    public void create(Group entity) {
        entity.setId(generateId());
        groups.add(entity);
        try (FileWriter fileWriter = new FileWriter(file)) {
            Gson gson = new Gson();
            gson.toJson(groups, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Group entity) {
        Group current = findById(entity.getId());
        current.setName(entity.getName());
        current.setNameMentor(entity.getNameMentor());
        try (FileWriter fileWriter = new FileWriter(file)) {
            Gson gson = new Gson();
            gson.toJson(groups, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        groups.remove(findById(id));
        try (FileWriter fileWriter = new FileWriter(file)) {
            Gson gson = new Gson();
            gson.toJson(groups, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Group findById(String id) {
        for (Group group : groups) {
            if (group.getId().equals(id)) {
                return group;
            }
        }
        throw new RuntimeException("Group does not exist with id= " + id);
    }

    @Override
    public Collection<Group> findAll() throws IOException {
        try (FileReader fileReader = new FileReader(file)) {
            Gson gson = new Gson();
            return gson.fromJson(fileReader, new TypeToken<Collection<Group>>() {
            }.getType());
        }
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
