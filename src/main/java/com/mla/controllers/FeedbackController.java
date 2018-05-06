
package com.mla.controllers;

import java.util.Optional;
import com.mla.models.Feedback;
import com.mla.repositories.FeedbackRepository;
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
public class FeedbackController {
    @Autowired
    FeedbackRepository topicRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/feedback")
    public Iterable<Feedback> topic() {
        return topicRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/feedback")
    public Feedback save(@RequestBody Feedback topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/feedback/{id}")
    public Optional<Feedback> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/feedback/{id}")
    public Feedback update(@PathVariable String id, @RequestBody Feedback topic) {
        Optional<Feedback> optcontact = topicRepository.findById(id);
        Feedback t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/feedback/{id}")
    public String delete(@PathVariable String id) {
        Optional<Feedback> optTopic = topicRepository.findById(id);
        Feedback topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


