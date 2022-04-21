package com.wissen.tinyurl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
@Service
public class KafkaPublish {


    private KafkaTemplate<String, String> template;

    public KafkaPublish(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    private String topic = "RequestCounter";

    @PostConstruct
    public void publishMessage() {
        template.send(topic, "8083");
    }
}
