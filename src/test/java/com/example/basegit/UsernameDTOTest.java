package com.example.basegit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class UsernameDTOTest {

    @Test
    public void testEqualsDTO() {
        UsernameDTO emptyConstructorDTO = new UsernameDTO();
        UsernameDTO usernameDTO = new UsernameDTO(1, "Test");
        emptyConstructorDTO.setId(1);
        emptyConstructorDTO.setType("Test");

        Assertions.assertEquals(true, Objects.equals(emptyConstructorDTO, usernameDTO));
    }

    @Test
    public void testNotEqualsDTO() {
        UsernameDTO emptyConstructorDTO = new UsernameDTO();
        UsernameDTO usernameDTO = new UsernameDTO(1, "Test");
        emptyConstructorDTO.setId(1);
        emptyConstructorDTO.setType("Test1");
        Assertions.assertNotEquals(true, Objects.equals(emptyConstructorDTO, usernameDTO));
    }

    @Test
    public void testDTONotNull() {
        UsernameDTO usernameDTO = new UsernameDTO(1, "Test");
        Assertions.assertNotNull(usernameDTO);
    }
}