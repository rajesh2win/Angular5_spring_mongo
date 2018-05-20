
package com.mla.controllers;

import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import com.mla.models.CustomNewsTopic;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import com.mla.models.NewsTopic;
import com.mla.repositories.NewsTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by rnallamothu on 4/15/18.
 */
@RestController
@CrossOrigin(origins = {"http://35.200.168.104:8080", "http://localhost:4200","http://localhost:3200","http://localhost:8080"})
public class NewsController {
    @Autowired
    NewsTopicRepository newstopicRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping(method = RequestMethod.GET, value = "/news")
    public Iterable<NewsTopic> topic() {
        Query query = new Query();
        query.with(new Sort(new Order(Direction.DESC, "createdDate")));
        return mongoTemplate.find(query,NewsTopic.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/news/list")
    public Iterable<NewsTopic> topicList() {
        Query query = new Query();
        query.fields().exclude("topicDetails");
        return mongoTemplate.find(query,NewsTopic.class);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/news")
    public NewsTopic save(@RequestBody NewsTopic topic) {
        topic.setCreatedDate(new Date());
        newstopicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/news/{id}")
    public Optional<NewsTopic> show(@PathVariable String id) {
        return newstopicRepository.findById(id);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/news/{id}")
    public NewsTopic update(@PathVariable String id, @RequestBody NewsTopic topic) {
        Optional<NewsTopic> optcontact = newstopicRepository.findById(id);
        NewsTopic t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getTopicDetails() != null)
            t.setTopicDetails(topic.getTopicDetails());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        newstopicRepository.save(t);
        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/news/{id}")
    public String delete(@PathVariable String id) {
        Optional<NewsTopic> optTopic = newstopicRepository.findById(id);
        NewsTopic topic = optTopic.get();
        newstopicRepository.delete(topic);
        return "";
    }
}


