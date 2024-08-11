package com.material.shared.dto;

import com.material.shared.enums.AuthorizationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationResponseDto {
    private UUID transactionId;
    private AuthorizationStatus authorizationStatus;
}
