package com.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.library.message.Student;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MessageSender {
     RabbitTemplate rabbitTemplate;
     Queue myQueue;
     Queue myStudent;
     Exchange fanoutExchangeHr;

    public void sendMessage(List<Student> student) throws JsonProcessingException {
//        rabbitTemplate.convertAndSend(myStudent.getName(), convertListToJson(student));
        rabbitTemplate.convertAndSend(fanoutExchangeHr.getName(),"", convertListToJson(student));
        System.out.println("Sent message: " + student.toString());
    }

    public static String convertListToJson(List<Student> studentList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(studentList);
    }

}