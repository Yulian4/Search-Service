package com.api.searchservice.util;

import org.springframework.util.StringUtils;

public class JwtUtil {

    public static String extractBearer(String authorizationHeader) {
        if (!StringUtils.hasText(authorizationHeader)) return null;
        if (authorizationHeader.startsWith("Bearer ")) return authorizationHeader.substring(7);
        return authorizationHeader;
    }
}
