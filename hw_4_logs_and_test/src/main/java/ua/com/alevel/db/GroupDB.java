package ua.com.alevel.db;

import ua.com.alevel.entity.Group;

import java.util.Arrays;
import java.util.UUID;

public class GroupDB {

    int index = 0;
    int SIZE = 0;
    private Group[] groups;
    private static GroupDB instance;

    private GroupDB() {
        groups = new Group[SIZE];
    }

    public static GroupDB getInstance() {
        if (instance == null) {
            instance = new GroupDB();
        }
        return instance;
    }

    private void add(Group group) {
        groups = Arrays.copyOf(groups, groups.length + 1);
        groups[groups.length - 1] = group;
    }

    public void create(Group group) {
        group.setId(generateId());
        add(group);
    }

    public void update(Group group) {
        Group current = findById(group.getId());
        current.setNameMentor(group.getNameMentor());
        current.setName(group.getName());
    }

    public void delete(String id) {
        Group userDelete = findById(id);
        int groupLength = groups.length;
        for (int i = 0; i < groups.length; i++) {
            if (groups[i].getId().equals(userDelete.getId())) groups[i] = null;
            index = i;
        }
        for (int i = index; i < groups.length - 1; i++) {
            groups[i] = groups[i + 1];
        }
        groups[groupLength - 1] = null;
    }


    public Group findById(String id) {
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] != null && groups[i].getId().equals(id)) {
                return groups[i];
            }
        }
        return null;
    }

    public Group[] findAll() {
        return groups;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
