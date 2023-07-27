package com.rabbitmq.library.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employee {
    @JsonProperty("employee_id")
    String employeeId;

    String name;

    @JsonProperty("birth_date")
    LocalDate birthDate;
}
