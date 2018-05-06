
package com.mla.controllers;

import java.util.Optional;
import com.mla.models.PhoneNumbers;
import com.mla.models.Topic;
import com.mla.repositories.PhoneNumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import java.util.Optional;

/**
 * Created by rnallamothu on 4/15/18.
 */
@RestController
@CrossOrigin(origins = {"http://35.200.168.104:8080", "http://localhost:4200","http://localhost:3200"})
public class PhoneNumbersController {
    @Autowired
    PhoneNumbersRepository topicRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/phonenumbers")
    public Iterable<PhoneNumbers> topic() {
        return topicRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/phonenumbers")
    public PhoneNumbers save(@RequestBody PhoneNumbers topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/phonenumbers/{id}")
    public Optional<PhoneNumbers> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/phonenumbers/{id}")
    public PhoneNumbers update(@PathVariable String id, @RequestBody PhoneNumbers topic) {
        Optional<PhoneNumbers> optcontact = topicRepository.findById(id);
        PhoneNumbers t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/phonenumbers/{id}")
    public String delete(@PathVariable String id) {
        Optional<PhoneNumbers> optTopic = topicRepository.findById(id);
        PhoneNumbers topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


