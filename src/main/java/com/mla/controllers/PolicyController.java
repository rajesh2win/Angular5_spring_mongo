
package com.mla.controllers;

import java.util.Optional;
import com.mla.models.Policy;
import com.mla.repositories.PolicyRepository;
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
public class PolicyController {
    @Autowired
    PolicyRepository topicRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/policy")
    public Iterable<Policy> topic() {
        return topicRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/policy")
    public Policy save(@RequestBody Policy topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/policy/{id}")
    public Optional<Policy> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/policy/{id}")
    public Policy update(@PathVariable String id, @RequestBody Policy topic) {
        Optional<Policy> optcontact = topicRepository.findById(id);
        Policy t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/policy/{id}")
    public String delete(@PathVariable String id) {
        Optional<Policy> optTopic = topicRepository.findById(id);
        Policy topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


