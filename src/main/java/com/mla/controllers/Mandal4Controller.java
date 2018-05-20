
package com.mla.controllers;

import com.mla.models.Mandal4;
import com.mla.repositories.Mandal4Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by rnallamothu on 4/15/18.
 */
@RestController
@CrossOrigin(origins = {"http://35.200.168.104:8080", "http://localhost:4200","http://localhost:3200"})
public class Mandal4Controller {
    @Autowired
    Mandal4Repository topicRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/mandal4")
    public Iterable<Mandal4> topic() {
        return topicRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/mandal4")
    public Mandal4 save(@RequestBody Mandal4 topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mandal4/{id}")
    public Optional<Mandal4> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/mandal4/{id}")
    public Mandal4 update(@PathVariable String id, @RequestBody Mandal4 topic) {
        Optional<Mandal4> optcontact = topicRepository.findById(id);
        Mandal4 t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/mandal4/{id}")
    public String delete(@PathVariable String id) {
        Optional<Mandal4> optTopic = topicRepository.findById(id);
        Mandal4 topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


