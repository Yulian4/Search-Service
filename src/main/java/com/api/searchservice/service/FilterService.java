package com.api.searchservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.searchservice.model.dto.RecipeDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilterService {

    private final RestTemplate restTemplate;

    public List<String> getUserRestrictions(String userId) {
        String url = "http://user-service/api/users/" + userId + "/restrictions";
        String[] restrictions = restTemplate.getForObject(url, String[].class);
        return List.of(restrictions);
    }

    public List<RecipeDTO> applyUserRestrictions(String userId, List<RecipeDTO> candidates) {

        List<String> restrictions = getUserRestrictions(userId);

        if (restrictions.isEmpty()) return candidates;

        return candidates.stream()
                .filter(r ->
                        r.getTags() != null &&
                        restrictions.stream().noneMatch(r.getTags()::contains)
                )
                .collect(Collectors.toList());
    }
}