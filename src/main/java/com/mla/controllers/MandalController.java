
package com.mla.controllers;

import java.util.Optional;
import com.mla.models.Mandal1;
import com.mla.models.Mandal;
import com.mla.repositories.MandalRepository;
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
public class MandalController {
    @Autowired
    MandalRepository topicRepository;

    @Autowired
    Mandal1Repository topicRepository1;

    @RequestMapping(method = RequestMethod.GET, value = "/mandal")
    public Iterable<Mandal> topic() {
        return topicRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/mandal")
    public Mandal save(@RequestBody Mandal topic) {
        topicRepository.save(topic);
        return topic;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/mandal/{id}")
    public Optional<Mandal> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/mandal/{id}")
    public Mandal update(@PathVariable String id, @RequestBody Mandal topic) {
        Optional<Mandal> optcontact = topicRepository.findById(id);
        Mandal t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/mandal/{id}")
    public String delete(@PathVariable String id) {
        Optional<Mandal> optTopic = topicRepository.findById(id);
        Mandal topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


