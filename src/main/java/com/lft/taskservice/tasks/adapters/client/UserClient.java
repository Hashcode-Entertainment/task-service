package com.lft.taskservice.tasks.adapters.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClient {

    private static final String USER_URL = "http://localhost:8080/users";
    private final RestTemplate restTemplate = new RestTemplate();

    public UserDto getUserById(Long id) {
        return restTemplate.getForObject(USER_URL + "/" + id, UserDto.class);
    }

}
