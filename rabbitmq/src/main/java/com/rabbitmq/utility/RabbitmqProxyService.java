package com.rabbitmq.utility;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Base64;
import java.util.List;

@Component
public class RabbitmqProxyService {
    public List<RabbitmqQueue> getAllQueues() {

        var webClient=WebClient.create("http://localhost:15672/api/queues");
        return webClient.get().header("Authorization", createBasicAuthHeaders()).retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RabbitmqQueue>>() {
                }).block(Duration.ofSeconds(10));
    }

    public String createBasicAuthHeaders() {
        // username:password for rabbitmq
        var auth = "kien:123";
        return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
    }
}
