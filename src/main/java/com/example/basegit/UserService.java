package com.example.basegit;

import java.util.List;

public class UserService {

    public UsernameDTO getUserById(Integer id) throws Exception {
        return users().stream().filter(usernameDTO -> usernameDTO.getId().equals(id)).findFirst().orElseThrow(() -> new Exception("User notfound!"));
    }

    private List<UsernameDTO> users() {
        return List.of(new UsernameDTO(1, "Test1"), new UsernameDTO(2, "Test3"), new UsernameDTO(3, "Test3"));
    }
}
