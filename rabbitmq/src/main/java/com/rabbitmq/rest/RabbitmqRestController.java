package com.rabbitmq.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RabbitmqRestController {

    RabbitTemplate rabbitTemplate;
    static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @PostMapping(path = { "/api/publish/{exchange}/{routingKey}",
            "/api/publish/{exchange}" })
    public ResponseEntity<String> publish(@PathVariable(name = "exchange", required = true) String exchange,
                                          @PathVariable(name = "routingKey", required = false) Optional<String> routingKey,
                                          @RequestBody String message) {
        if (!isValidJson(message)) {
            return ResponseEntity.badRequest().body(Boolean.FALSE.toString());
        }

        try {
            rabbitTemplate.convertAndSend(exchange, routingKey.orElse(""), message);
            return ResponseEntity.ok().body(Boolean.TRUE.toString());
        } catch (Exception e) {
            log.error("Error when publishing : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public static boolean isValidJson(String string) {
        try {
            OBJECT_MAPPER.readTree(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
