package com.dikkulah.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "gateway-service", url = "http://localhost:1111/api/authentication")
public interface GatewayClient {

    @PostMapping("validateToken")
    ResponseEntity<String> validate(@RequestHeader("Authorization") String token, @RequestBody String request);
}
