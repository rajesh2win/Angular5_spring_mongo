
package com.mla.controllers;

import com.mla.models.Work;
import com.mla.models.WorkCategory;
import com.mla.repositories.CustomWorkRepository;
import com.mla.repositories.WorkCategoryRepository;
import com.mla.repositories.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by rnallamothu on 4/15/18.
 */
@RestController
@CrossOrigin(origins = {"http://35.200.168.104:8080", "http://localhost:4200","http://localhost:3200"})
public class WorkCategoryController {
    @Autowired
    WorkCategoryRepository topicRepository;
    @Autowired
    CustomWorkRepository customWorkRepository;
    String item ="";

    @RequestMapping(method = RequestMethod.GET, value = "/workcategory")
    public Iterable<WorkCategory> topic() {
        return topicRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/workcategory")
    public WorkCategory save(@RequestBody WorkCategory topic) {
        topicRepository.save(topic);
        return topic;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/workcategory/{id}")
    public Optional<WorkCategory> show(@PathVariable String id) {
        Optional<WorkCategory> optcontact = topicRepository.findById(id);
        item = optcontact.get().getTopicName();
        return optcontact;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/workcategory/{id}")
    public WorkCategory update(@PathVariable String id, @RequestBody WorkCategory topic) {
        Optional<WorkCategory> optcontact = topicRepository.findById(id);
        WorkCategory t = optcontact.get();
        if (topic.getTopicName() != null)
            t.setTopicName(topic.getTopicName());
        if (topic.getImageUrl() != null)
            t.setImageUrl(topic.getImageUrl());

        topicRepository.save(t);

        Iterable<Work> works = customWorkRepository.findByItem(item);
        for(Work w:works) {
            if (w.getItem() != null)
                w.setItem(topic.getTopicName());
            customWorkRepository.save(w);
        }

        return t;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/workcategory/{id}")
    public String delete(@PathVariable String id) {
        Optional<WorkCategory> optTopic = topicRepository.findById(id);
        WorkCategory topic = optTopic.get();
        topicRepository.delete(topic);

        return "";
    }
}


