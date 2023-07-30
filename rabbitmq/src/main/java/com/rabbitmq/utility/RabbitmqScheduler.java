package com.rabbitmq.utility;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

//@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RabbitmqScheduler {
    RabbitmqProxyService rabbitmqProxyService;

    @Scheduled(fixedDelay = 9000)
    public void sweepDirtyQueues() {
        var dirtyQueues =rabbitmqProxyService.getAllQueues().stream().filter(RabbitmqQueue::isDirty).collect(Collectors.toList());

        dirtyQueues.forEach(q -> log.info("Queue {} has {} unprocessed messages", q.getName(), q.getMessages()));
    }
}
