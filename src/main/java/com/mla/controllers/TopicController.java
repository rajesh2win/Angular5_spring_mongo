package com.mla.controllers;

import com.mla.models.Topic;
import com.mla.models.NewsTopic;
import com.mla.repositories.TopicRepository;
import com.mla.repositories.CustomTopicRepository;
import com.mla.repositories.TopicImageRepository;
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
@CrossOrigin(origins = {"http://35.200.168.104:8080","http://localhost:4200","http://localhost:3200"})
public class TopicController {
    @Autowired
    TopicRepository topicRepository;

    @Autowired
    CustomTopicRepository customTopicRepository;

    @Autowired
    TopicImageRepository topicImageRepository;

    @RequestMapping(method= RequestMethod.GET, value="/topics")
    public Iterable<Topic> topic() {
        return topicRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/topics")
    public Topic save(@RequestBody Topic topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method=RequestMethod.GET, value="/topics/{id}")
    public Optional<Topic> show(@PathVariable String id) {
        return topicRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.GET, value="/topics/topicimagename/{topicName}")
    public Optional<NewsTopic> showNames(@PathVariable String topicName) {
        return topicImageRepository.findByTopicName(topicName);
    }

    @RequestMapping(method=RequestMethod.GET, value="/topics/topicname/{topicName}")
    public Optional<Topic> showTopicWithImageNames(@PathVariable String topicName) {
        return customTopicRepository.findByTopicName(topicName);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
    public Topic update(@PathVariable String id, @RequestBody Topic topic) {
        Optional<Topic> optcontact = topicRepository.findById(id);
        Topic t = optcontact.get();
        if(topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if(topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        topicRepository.save(t);
        return t;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
    public String delete(@PathVariable String id) {
        Optional<Topic> optTopic = topicRepository.findById(id);
        Topic topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}

