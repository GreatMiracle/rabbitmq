package com.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbitmq.entity.Student;
import com.rabbitmq.producer.MessageSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static com.rabbitmq.producer.MessageSender.convertListToJson;

@SpringBootApplication
public class RabbitmqApplication {

	public static void main(String[] args) throws JsonProcessingException {
		ApplicationContext context = SpringApplication.run(RabbitmqApplication.class, args);


		Student stu = Student.builder()
				.id(1L)
				.name("kien")
				.address("Hanoi")
				.age(25)
				.build();


		Student stu2 = Student.builder()
				.id(2L)
				.name("quan")
				.address("Thanh Hoa")
				.age(24)
				.build();

		List<Student> studentList = new ArrayList<>();
		studentList.add(stu);
		studentList.add(stu2);

		// Gửi tin nhắn
		MessageSender sender = context.getBean(MessageSender.class);
//		sender.sendMessage("Hello, RabbitMQ!");
//		sender.sendMessage("abc");
		sender.sendMessage(studentList);

	}

}
