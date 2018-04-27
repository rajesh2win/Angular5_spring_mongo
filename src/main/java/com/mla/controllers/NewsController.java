
package com.mla.controllers;

import java.util.Optional;
import com.mla.models.NewsTopic;
import com.mla.repositories.NewsTopicRepository;
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
@CrossOrigin(origins = {"http://35.200.168.104:8080", "http://localhost:4200","http://localhost:3200"})
public class NewsController {
    @Autowired
    NewsTopicRepository topicRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/news")
    public Iterable<NewsTopic> topic() {
        return topicRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/news")
    public NewsTopic save(@RequestBody NewsTopic topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/news/{id}")
    public Optional<NewsTopic> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/news/{id}")
    public NewsTopic update(@PathVariable String id, @RequestBody NewsTopic topic) {
        Optional<NewsTopic> optcontact = topicRepository.findById(id);
        NewsTopic t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/news/{id}")
    public String delete(@PathVariable String id) {
        Optional<NewsTopic> optTopic = topicRepository.findById(id);
        NewsTopic topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


