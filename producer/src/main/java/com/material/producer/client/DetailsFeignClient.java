package com.material.producer.client;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "authorization-service", url = "http://localhost:8084", fallback = DetailsFeignClient.DetailsFallbackImpl.class)
public interface DetailsFeignClient {
    @GetMapping("/list-cards")
    List<String> listAllCards(URI baseUrl);

    @GetMapping("/list-merchants")
    List<Integer> listAllMerchants(URI baseUrl);


    class DetailsFallbackImpl implements DetailsFeignClient {

        @Override
        public List<String> listAllCards(URI baseUrl) {
            return new ArrayList<>();
        }

        @Override
        public List<Integer> listAllMerchants(URI baseUrl) {
            return new ArrayList<>();
        }
    }
}
