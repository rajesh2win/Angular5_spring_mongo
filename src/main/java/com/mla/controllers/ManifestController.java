
package com.mla.controllers;

import java.util.Optional;
import com.mla.models.ManifestTopic;
import com.mla.repositories.ManifestTopicRepository;
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
public class ManifestController {
    @Autowired
    ManifestTopicRepository topicRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/manifest")
    public Iterable<ManifestTopic> topic() {
        return topicRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/manifest")
    public ManifestTopic save(@RequestBody ManifestTopic topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/manifest/{id}")
    public Optional<ManifestTopic> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/manifest/{id}")
    public ManifestTopic update(@PathVariable String id, @RequestBody ManifestTopic topic) {
        Optional<ManifestTopic> optcontact = topicRepository.findById(id);
        ManifestTopic t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/manifest/{id}")
    public String delete(@PathVariable String id) {
        Optional<ManifestTopic> optTopic = topicRepository.findById(id);
        ManifestTopic topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


