package com.example.basegit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void getUserById() {
        UserService userService = new UserService();
        UsernameDTO expected = new UsernameDTO(1, "Test1");
        try {
            UsernameDTO user = userService.getUserById(1);
            Assertions.assertEquals(expected, user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}