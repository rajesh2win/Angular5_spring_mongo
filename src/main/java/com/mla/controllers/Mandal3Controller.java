
package com.mla.controllers;

import com.mla.models.Mandal3;
import com.mla.repositories.Mandal3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by rnallamothu on 4/15/18.
 */
@RestController
@CrossOrigin(origins = {"http://35.200.168.104:8080", "http://localhost:4200","http://localhost:3200"})
public class Mandal3Controller {
    @Autowired
    Mandal3Repository topicRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/mandal3")
    public Iterable<Mandal3> topic() {
        return topicRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/mandal3")
    public Mandal3 save(@RequestBody Mandal3 topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mandal3/{id}")
    public Optional<Mandal3> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/mandal3/{id}")
    public Mandal3 update(@PathVariable String id, @RequestBody Mandal3 topic) {
        Optional<Mandal3> optcontact = topicRepository.findById(id);
        Mandal3 t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/mandal3/{id}")
    public String delete(@PathVariable String id) {
        Optional<Mandal3> optTopic = topicRepository.findById(id);
        Mandal3 topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


