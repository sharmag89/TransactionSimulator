package com.material.authorization.controller;

import com.material.shared.dto.AuthorizationResponseDto;
import com.material.shared.dto.TransactionLogDto;
import com.material.authorization.service.AuthorizationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @PostMapping("/authorize")
    public AuthorizationResponseDto authorizeTransaction(@RequestBody TransactionLogDto transactionLogDto) {
        log.info("Recieved Auth Request for : "+transactionLogDto);
        return authorizationService.authorizeTransaction(transactionLogDto);
    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthorizationResponseDto> authenticate(@RequestBody TransactionLogDto transactionLogDto) {
//        User authenticatedUser = authenticationService.authenticate(loginUserDto);
//
//        String jwtToken = jwtService.generateToken(authenticatedUser);
//
//        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
//
//        return ResponseEntity.ok(loginResponse);
//    }
}
