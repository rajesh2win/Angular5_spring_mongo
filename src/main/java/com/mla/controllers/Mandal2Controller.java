
package com.mla.controllers;

import com.mla.models.Mandal2;
import com.mla.repositories.Mandal2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by rnallamothu on 4/15/18.
 */
@RestController
@CrossOrigin(origins = {"http://35.200.168.104:8080", "http://localhost:4200","http://localhost:3200"})
public class Mandal2Controller {
    @Autowired
    Mandal2Repository topicRepository;


    @RequestMapping(method = RequestMethod.GET, value = "/mandal2")
    public Iterable<Mandal2> topic() {
        return topicRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/mandal2")
    public Mandal2 save(@RequestBody Mandal2 topic) {
        topicRepository.save(topic);
        return topic;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/mandal2/{id}")
    public Optional<Mandal2> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/mandal2/{id}")
    public Mandal2 update(@PathVariable String id, @RequestBody Mandal2 topic) {
        Optional<Mandal2> optcontact = topicRepository.findById(id);
        Mandal2 t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/mandal2/{id}")
    public String delete(@PathVariable String id) {
        Optional<Mandal2> optTopic = topicRepository.findById(id);
        Mandal2 topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


