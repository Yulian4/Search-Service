package com.api.searchservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccessControlService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String validateToken(String token) {
        String url = "http://auth-service/api/auth/validate";
        return restTemplate.postForObject(url, token, String.class);
    }

    public <T> List<T> applyUserVisibilityLimits(String userId, List<T> recipes) {

        String typeUrl = "http://user-service/api/users/" + userId + "/type";
        String userType = restTemplate.getForObject(typeUrl, String.class);

        if ("premium".equalsIgnoreCase(userType)) {
            return recipes;
        }

        return recipes.stream().limit(5).toList();
    }
}
