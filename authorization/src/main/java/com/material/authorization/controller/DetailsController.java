package com.material.authorization.controller;

import com.material.authorization.service.DetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DetailsController {
    private final DetailsService detailsService;

    @GetMapping("/list-cards")
    public List<String> listAllCards() {
        return detailsService.listAllCards();
    }

    @GetMapping("/list-merchants")
    public List<Integer> listAllMerchants() {
        return detailsService.listAllMerchants();
    }
}
