
package com.mla.controllers;

import com.mla.models.Mandal5;
import com.mla.repositories.Mandal5Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by rnallamothu on 4/15/18.
 */
@RestController
@CrossOrigin(origins = {"http://35.200.168.104:8080", "http://localhost:4200","http://localhost:3200"})
public class Mandal5Controller {
    @Autowired
    Mandal5Repository topicRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/mandal5")
    public Iterable<Mandal5> topic() {
        return topicRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/mandal5")
    public Mandal5 save(@RequestBody Mandal5 topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mandal5/{id}")
    public Optional<Mandal5> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/mandal5/{id}")
    public Mandal5 update(@PathVariable String id, @RequestBody Mandal5 topic) {
        Optional<Mandal5> optcontact = topicRepository.findById(id);
        Mandal5 t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/mandal5/{id}")
    public String delete(@PathVariable String id) {
        Optional<Mandal5> optTopic = topicRepository.findById(id);
        Mandal5 topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


