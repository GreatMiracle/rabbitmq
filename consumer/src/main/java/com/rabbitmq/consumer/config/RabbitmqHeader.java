package com.rabbitmq.consumer.config;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RabbitmqHeader {

    private static final String KEYWORD_QUEUE_WAIT = "wait";
    private List<RabbitmqHeaderXDeath> xDeaths = new ArrayList<>(2);
    private String xFirstDeathExchange = StringUtils.EMPTY;
    private String xFirstDeathQueue = StringUtils.EMPTY;
    private String xFirstDeathReason = StringUtils.EMPTY;

    @SuppressWarnings("unchecked")
    public RabbitmqHeader(Map<String, Object> headers) {
        if (headers != null) {
            var xFDExchange = Optional.ofNullable(headers.get("x-first-death-exchange"));
            var xFDQueue = Optional.ofNullable(headers.get("x-first-death-queue"));
            var xFDReason = Optional.ofNullable(headers.get("x-first-death-reason"));

            xFDExchange.ifPresent(s -> xFirstDeathExchange = s.toString());
            xFDQueue.ifPresent(s -> xFirstDeathQueue =s.toString());
            xFDReason.ifPresent(s -> xFirstDeathReason = s.toString());

            var xDeathHeaders = (List<Map<String, Object>>) headers.get("x-death");

            if (xDeathHeaders != null) {
                for (Map<String, Object> x : xDeathHeaders) {
                    RabbitmqHeaderXDeath hdrDeath = new RabbitmqHeaderXDeath();
                    var reason = Optional.ofNullable(x.get("reason"));
                    var count = Optional.ofNullable(x.get("count"));
                    var exchange = Optional.ofNullable(x.get("exchange"));
                    var queue = Optional.ofNullable(x.get("queue"));
                    var routingKeys = Optional.ofNullable(x.get("routing-keys"));
                    var time = Optional.ofNullable(x.get("time"));

                    reason.ifPresent(s -> hdrDeath.setReason(s.toString()));
                    count.ifPresent(s -> hdrDeath.setCount(Integer.parseInt(s.toString())));
                    exchange.ifPresent(s -> hdrDeath.setExchange(s.toString()));
                    queue.ifPresent(s -> hdrDeath.setQueue(s.toString()));
                    routingKeys.ifPresent(r -> {
                        var listR = (List<String>) r;
                        hdrDeath.setRoutingKeys(listR);
                    });
                    time.ifPresent(d -> hdrDeath.setTime((Date) d));

                    xDeaths.add(hdrDeath);
                }
            }
        }
    }

    public int getFailedRetryCount() {
        // get from queue "wait"
        for (var xDeath : xDeaths) {
            if (xDeath.getExchange().toLowerCase().endsWith(KEYWORD_QUEUE_WAIT)
                    && xDeath.getQueue().toLowerCase().endsWith(KEYWORD_QUEUE_WAIT)) {
                return xDeath.getCount();
            }
        }

        return 0;
    }

}
