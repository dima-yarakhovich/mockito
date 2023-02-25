package com.skypro.user;

import java.util.*;

public class UserRepository {
    private final List<User> userList = new ArrayList<>();

    public Collection<User> getAllUsers() {
        return userList;
    }

    public Optional<User> getAllUserByLogin(String login) {
        return this.userList.stream().
                filter(user -> user.getLogin().equals(login)).
                findAny();
    }

    public Optional<User> getAllUserByParameters(String login, String password) {
        return this.userList.stream().
                filter(user -> user.getLogin().equals(login)).
                filter(user -> user.getPassword().equals(password)).
                findAny();
    }

    public void addUser(User user) {
        this.userList.add(user);
    }


}
