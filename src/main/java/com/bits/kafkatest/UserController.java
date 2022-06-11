package com.bits.kafkatest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/kafka")
public class UserController {
    private final UserProducer userProducer;

    @Autowired
    UserController(UserProducer userProducer) {
        this.userProducer = userProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic() {
        User user = new User();
        user.setName(RandomStringUtils.randomAlphabetic(5));
        user.setCurrentTime(LocalDateTime.now().toString());
        this.userProducer.sendMessage(user);
    }
}
