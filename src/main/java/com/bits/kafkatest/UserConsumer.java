package com.bits.kafkatest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {
    private static final Logger logger = LoggerFactory.getLogger(UserConsumer.class);

    @KafkaListener(topics = AppConstant.TOPIC, groupId = AppConstant.GROUP_ID)
    public void consumeUser(User user){
        logger.info(String.format("#### -> Consuming message -> %s", user.toString()));
    }
}
