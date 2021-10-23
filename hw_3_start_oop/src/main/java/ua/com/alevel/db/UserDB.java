package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class UserDB {

    int index = 0;
    int SIZE = 0;
    private User[] users;
    private static UserDB instance;

    private UserDB() {
        users = new User[SIZE];
    }

    public static UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

    private  void add(User user){
        users = Arrays.copyOf(users, users.length+1);
        users[users.length-1] = user;
    }


    public void create(User user) {
        user.setId(generateId());
        add(user);
    }

    public void update(User user) {
        User current = findById(user.getId());
        current.setAge(user.getAge());
        current.setName(user.getName());
    }

    public void delete(String id) {
        User userDelete = findById(id);
        for (int i = 0 ; i< users.length; i++){
            if(users[i].getId().equals(String.valueOf(userDelete.getId()))) users[i] = null;
            index = i;
        }
        User arrayUserDelete[]  =  new User[users.length-1];
        for (int i = 0 ; i<index; i++){
            arrayUserDelete[i] = users[i];
        }
        for (int i = index; i<arrayUserDelete.length; i++){
            arrayUserDelete[i] = users[i+1];
        }
        users = Arrays.copyOf(users, users.length-1);
    }

    public User findById(String id) {
        for (User user: users) {
            if(user.getId().equals(id)) return user;
        }
        return null;

    }

    public User[] findAll() {
        return users;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}
