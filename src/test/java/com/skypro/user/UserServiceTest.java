package com.skypro.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @Test
    void getAllUserList() {
        Assertions.assertNotNull(userRepository);
        Mockito.when(userRepository.getAllUsers()).
                thenReturn(List.of(new User("Test", "Test")));
        List<String> test = List.of("Test");
        Assertions.assertEquals(userService.getAllUserList("Test"), test);
    }


    @Test
    void verifyLogin() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.addUser(null, "Test"));
    }


    @Test
    void verifyPassword() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.addUser("Test", null));
    }
    @Test
    void createUser() {
        Assertions.assertNotNull(userRepository);
        userService.addUser("Test", "Test");
        Mockito.verify(userRepository).addUser(Mockito.any());
    }
}