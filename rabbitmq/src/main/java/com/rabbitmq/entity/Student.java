package com.rabbitmq.entity;

import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student implements Serializable {
    Long id;
    String name;
    String address;
    Integer age;

}
