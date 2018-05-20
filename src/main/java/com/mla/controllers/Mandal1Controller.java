
package com.mla.controllers;

import java.util.Optional;
import com.mla.models.Mandal1;
import com.mla.repositories.Mandal1Repository;
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
public class Mandal1Controller {
    @Autowired
    Mandal1Repository topicRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/mandal1")
    public Iterable<Mandal1> topic() {
        return topicRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/mandal1")
    public Mandal1 save(@RequestBody Mandal1 topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mandal1/{id}")
    public Optional<Mandal1> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/mandal1/{id}")
    public Mandal1 update(@PathVariable String id, @RequestBody Mandal1 topic) {
        Optional<Mandal1> optcontact = topicRepository.findById(id);
        Mandal1 t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/mandal1/{id}")
    public String delete(@PathVariable String id) {
        Optional<Mandal1> optTopic = topicRepository.findById(id);
        Mandal1 topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


