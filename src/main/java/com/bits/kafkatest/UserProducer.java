package com.bits.kafkatest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class UserProducer {
    private static final Logger logger = LoggerFactory.getLogger(UserProducer.class);


    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void sendMessage(User user) {
        logger.info(String.format("#### -> Producing message -> %s", user.toString()));
        this.kafkaTemplate.send(AppConstant.TOPIC, user);
    }
}
