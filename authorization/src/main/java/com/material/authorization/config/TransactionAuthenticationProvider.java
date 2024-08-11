package com.material.authorization.config;

//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;

//@Component
//public class TransactionAuthenticationProvider implements AuthenticationProvider {
//
//    @Override
//    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
//        final String name = authentication.getName();
//        final String password = authentication.getCredentials().toString();
//        if (!"admin".equals(name) || !"system".equals(password)) {
//            return null;
//        }
//        return authenticateAgainstThirdPartyAndGetAuthentication(name, password);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return TransactionAuthenticationProvider.class.isAssignableFrom(authentication);
//    }
//}