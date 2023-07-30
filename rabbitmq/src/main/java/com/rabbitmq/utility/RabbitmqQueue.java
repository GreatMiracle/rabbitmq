package com.rabbitmq.utility;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RabbitmqQueue {
    @JsonProperty
    long messages;

    @JsonProperty
    String name;

    public boolean isDirty() {
        return messages > 0;
    }
}
