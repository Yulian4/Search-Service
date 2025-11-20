package com.api.searchservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "${auth.service.url:http://localhost:8082}")
public interface AuthServiceClient {
    @GetMapping("/api/v1/auth/verify-token")
    UserVerificationResponse verifyToken(@RequestHeader("Authorization") String bearerToken);

    class UserVerificationResponse {
        public boolean valid;
        public String userId;
        // other fields like roles, restrictions, etc.
    }
}