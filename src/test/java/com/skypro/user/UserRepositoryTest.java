package com.skypro.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;



public class UserRepositoryTest {
    UserRepository userRepository;

    @Test
    public void getAllUserByNull() {
        UserRepository userRepository = new UserRepository();
        List<User> expected = userRepository.getAllUsers().
                stream().
                toList();
        Assertions.assertTrue(expected.isEmpty());
    }

    @Test
    public void getAllUser() {
        UserRepository userRepository = new UserRepository();
        User example1 = new User("Dima", "3333333333");
        User example2 = new User("Dimas", "Dima");
        userRepository.addUser(example1);
        userRepository.addUser(example2);
        List<User> expected = new ArrayList<>();
        expected.add(example1);
        expected.add(example2);
        List<User> actual = (List<User>) userRepository.getAllUsers();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchUsersByLogin() {
        User userTest = new User("Test", "Test");
        userRepository.addUser(userTest);
        User user = userRepository.getAllUserByLogin("Test").get();
        Assertions.assertEquals(userTest, user);
    }

    @Test
    public void getUserByLoginWithNull() {
        User userTest = new User("Test", "Test");
        userRepository.addUser(userTest);
        User user = userRepository.getAllUserByLogin("Test1").orElse(null);
        Assertions.assertNull(user);
    }

    @Test
    public void searchUsersByPasswordWithNotEqualsLogin() {
        User userTest = new User("Test", "Test");
        userRepository.addUser(userTest);
        User user = userRepository.getAllUserByParameters("Crash", "Test").orElse(null);
        Assertions.assertNull(user);
    }

    @Test
    public void searchUsersByLoginWithNotEqualsPassword() {
        User userTest = new User("Test", "Test");
        userRepository.addUser(userTest);
        User user = userRepository.getAllUserByParameters("Test", "Crash").orElse(null);
        Assertions.assertNull(user);
    }


}