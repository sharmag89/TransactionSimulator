package com.material.consumer.client;

import com.material.shared.enums.AuthorizationStatus;
import com.material.shared.dto.AuthorizationResponseDto;
import com.material.shared.dto.TransactionLogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

@FeignClient(name = "authorization-service", url = "http://localhost:8084", fallback = AuthorizationFeignClient.AuthorizationFallbackImpl.class)
public interface AuthorizationFeignClient {
    @PostMapping("/authorize")
    AuthorizationResponseDto authorizeTransaction(URI baseUrl, @RequestBody TransactionLogDto transactionLogDto);


    class AuthorizationFallbackImpl implements AuthorizationFeignClient {

        @Override
        public AuthorizationResponseDto authorizeTransaction(URI baseUrl, TransactionLogDto transactionLogDto) {
            return new AuthorizationResponseDto(transactionLogDto.getTransactionId(), AuthorizationStatus.IN_PROGRESS);
        }
    }
}
