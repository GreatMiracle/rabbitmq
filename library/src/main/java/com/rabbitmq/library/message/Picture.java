package com.rabbitmq.library.message;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Picture {
    String name;
    String type;
    String source;
    long size;
}
