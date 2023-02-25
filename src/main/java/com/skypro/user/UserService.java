package com.skypro.user;


import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<String> getAllUserList(String login) {
        return userRepository.getAllUsers().
                stream().
                map(User::getLogin).
                collect(Collectors.toList());
    }

    public void addUser(String userLogin, String userPassword) {
        User user = new User(userLogin, userPassword);
        if (userLogin == null || userLogin.isBlank()) {
            throw new IllegalArgumentException("User login should be defined");
        }
        boolean userExist = this.userRepository.
                getAllUsers().
                stream().
                anyMatch(t -> equals(user));
        if (userExist) {
            throw new UserNonUniqueException();
        }
        this.userRepository.addUser(user);
    }


}
