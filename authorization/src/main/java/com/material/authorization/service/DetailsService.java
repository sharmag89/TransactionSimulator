package com.material.authorization.service;

import com.material.authorization.repository.CardRepository;
import com.material.authorization.repository.MerchantRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DetailsService {
    private CardRepository cardRepository;
    private MerchantRepository merchantRepository;

    @Transactional
    public List<String> listAllCards() {
        return cardRepository.getAllIds();
    }

    @Transactional
    public List<Integer> listAllMerchants() {
        return merchantRepository.getAllIds();
    }
}
