package com.bogdan.rabbitmqexample.controller;

import com.bogdan.rabbitmqexample.model.User;
import com.bogdan.rabbitmqexample.producer.RabbitMQJsonProducer;
import com.bogdan.rabbitmqexample.producer.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private RabbitMQProducer producer;

    private RabbitMQJsonProducer jsonProducer;

    public MessageController(RabbitMQProducer producer, RabbitMQJsonProducer jsonProducer) {
        this.producer = producer;
        this.jsonProducer = jsonProducer;
    }

    @GetMapping
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ!");
    }

    @PostMapping
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json message sent to RabbitMQ!");
    }
}
