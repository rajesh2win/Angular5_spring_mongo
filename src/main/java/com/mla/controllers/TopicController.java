package com.mla.controllers;

import com.mla.models.Topic;
import com.mla.repositories.TopicRepository;
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
@CrossOrigin(origins = "http://35.229.43.8:8080")
public class TopicController {
    @Autowired
    TopicRepository topicRepository;

    @RequestMapping(method= RequestMethod.GET, value="/topics")
    @CrossOrigin(origins = "http://35.229.43.8:8080")
    public Iterable<Topic> topic() {
        return topicRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/topics")
    @CrossOrigin(origins = "http://35.229.43.8:8080")
    public Topic save(@RequestBody Topic topic) {
        System.out.println("--------"+topic.getTopicName());
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method=RequestMethod.GET, value="/topics/{id}")
    @CrossOrigin(origins = "http://35.229.43.8:8080")
    public Optional<Topic> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
    @CrossOrigin(origins = "http://35.229.43.8:8080")
    public Topic update(@PathVariable String id, @RequestBody Topic topic) {
        Optional<Topic> optcontact = topicRepository.findById(id);
        Topic t = optcontact.get();
        if(topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if(topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
    public String delete(@PathVariable String id) {
        Optional<Topic> optTopic = topicRepository.findById(id);
        Topic topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}

