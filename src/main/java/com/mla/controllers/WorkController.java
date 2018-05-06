
package com.mla.controllers;

import java.util.Optional;
import com.mla.models.Work;
import com.mla.repositories.WorkRepository;
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
public class WorkController {
    @Autowired
    WorkRepository topicRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/work")
    public Iterable<Work> topic() {
        return topicRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/work")
    public Work save(@RequestBody Work topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/work/{id}")
    public Optional<Work> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/work/{id}")
    public Work update(@PathVariable String id, @RequestBody Work topic) {
        Optional<Work> optcontact = topicRepository.findById(id);
        Work t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/work/{id}")
    public String delete(@PathVariable String id) {
        Optional<Work> optTopic = topicRepository.findById(id);
        Work topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


