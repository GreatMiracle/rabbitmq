package com.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbitmq.library.message.Employee;
import com.rabbitmq.library.message.Picture;
//import com.rabbitmq.producer.MessageSender;
//import com.rabbitmq.producer.PictureProducer;
import com.rabbitmq.producer.MyPictureProducer;
import com.rabbitmq.producer.PictureProducerTwo;
import com.rabbitmq.producer.RetryEmployeeProducer;
import com.rabbitmq.producer.RetryPictureProducer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RabbitmqApplication implements CommandLineRunner {

//    PictureProducer pictureProducer;
//    MessageSender messageSender;
//    PictureProducerTwo pictureProducerTwo;
//    MyPictureProducer myPictureProducer;
//    RetryPictureProducer retryPictureProducer;
//    RetryEmployeeProducer employeeProducer;
//
//    List<String> SOURCES = List.of("mobile", "web");
//    List<String> TYPES = List.of("jpg", "png", "svg");

    public static void main(String[] args) throws JsonProcessingException {
        ApplicationContext context = SpringApplication.run(RabbitmqApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Student stu = Student.builder()
//                .id(1L)
//                .name("kien")
//                .address("Hanoi")
//                .age(25)
//                .build();
//
//
//        Student stu2 = Student.builder()
//                .id(2L)
//                .name("quan")
//                .address("Thanh Hoa")
//                .age(24)
//                .build();
//
//        List<Student> studentList = new ArrayList<>();
//        studentList.add(stu);
//        studentList.add(stu2);
//
//        // Gửi tin nhắn
////		MessageSender sender = context.getBean(MessageSender.class);
////		sender.sendMessage("Hello, RabbitMQ!");
////		sender.sendMessage("abc");
//        messageSender.sendMessage(studentList);

//        for (int i = 0; i < 10; i++) {
//            var p = Picture.builder()
//                    .name("Picture " + i)
////                    .size(ThreadLocalRandom.current().nextLong(1, 10001))
//                    .size(ThreadLocalRandom.current().nextLong(9001, 10001))
//                    .source(SOURCES.get(i % SOURCES.size()))
//                    .type(TYPES.get(i % TYPES.size()))
//                    .build();
//
////            pictureProducer.sendMessage(p);
////            pictureProducerTwo.sendMessage(p);
////            myPictureProducer.sendMessage(p);
////            retryPictureProducer.sendMessage(p);
//        }


//        for (int i = 0; i < 10; i++) {
//            Employee emp = new Employee("Employee" + i, null, LocalDate.now());
//            employeeProducer.sendMessage(emp);
//        }

    }
}
