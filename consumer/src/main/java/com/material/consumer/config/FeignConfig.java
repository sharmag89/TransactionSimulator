package com.material.consumer.config;


import java.util.Optional;


public class FeignConfig {

    //private static final ObjectMapper jsonMapper = new ObjectMapper();

//    @Bean
//    public OkHttpClient client() {
//        return new OkHttpClient();
//    }

//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return requestTemplate -> {
//            UserDetails user = null;
//            Optional<UserDetails> currentUser = UserSession.getInstance().getCurrentUser();
//            if (currentUser.isPresent()) {
//                user = currentUser.get();
//            }
//
//            try {
//                requestTemplate.header("X_USER_INFO", jsonMapper.writeValueAsString(user));
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        };
//    }
}

