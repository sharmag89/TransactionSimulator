package com.material.producer.controller;

import com.material.producer.service.TransactionProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
@AllArgsConstructor
@Slf4j
public class PublishController {
    private TransactionProducer transactionProducer;

    @PostMapping("/auto")
    public void autoPublish(@RequestParam Integer messageCount, @RequestParam Integer timeInSeconds) {
        log.info("Recieved AutoPublish Request with "+messageCount+" Messages.");
        transactionProducer.autoProduce(messageCount, timeInSeconds);
    }
}
