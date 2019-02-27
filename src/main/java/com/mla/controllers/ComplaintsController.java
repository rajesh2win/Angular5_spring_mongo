
package com.mla.controllers;

import java.util.Optional;

import com.mla.models.Complaints;
import com.mla.models.Complaints1;
import com.mla.repositories.ComplaintsRepository1;
import com.mla.repositories.ComplaintsRepository;
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
public class ComplaintsController {
    @Autowired
    ComplaintsRepository topicRepository;
    @Autowired
    ComplaintsRepository1 topicRepository1;

    @RequestMapping(method = RequestMethod.GET, value = "/complaints")
    public Iterable<Complaints> topic() {
        return topicRepository.findAll();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/complaintsall")
    public Iterable<Complaints1> topic1() {
        return topicRepository1.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/complaints")
    public Complaints save(@RequestBody Complaints topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/complaints/{id}")
    public Optional<Complaints> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/complaintsidall/{id}")
    public Optional<Complaints1> showall(@PathVariable String id) {
        return topicRepository1.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/complaints/{id}")
    public Complaints update(@PathVariable String id, @RequestBody Complaints topic) {
        Optional<Complaints> optcontact = topicRepository.findById(id);
        Complaints t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/complaints/{id}")
    public String delete(@PathVariable String id) {
        Optional<Complaints> optTopic = topicRepository.findById(id);
        Complaints topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


