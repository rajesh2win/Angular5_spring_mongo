package com.mla.controllers;

import com.mla.models.Ads;
import com.mla.repositories.AdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AdsController {

    @Autowired
    AdsRepository contactRepository;

    @RequestMapping(method=RequestMethod.GET, value="/ads")
    public Iterable<Ads> contact() {
        return contactRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/ads")
    public Ads save(@RequestBody Ads contact) {
        contactRepository.save(contact);

        return contact;
    }

    @RequestMapping(method=RequestMethod.GET, value="/ads/{id}")
    public Optional<Ads> show(@PathVariable String id) {
        return contactRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/ads/{id}")
    public Ads update(@PathVariable String id, @RequestBody Ads contact) {
        Optional<Ads> optcontact = contactRepository.findById(id);
        Ads c = optcontact.get();
        if(contact.getName() != null)
            c.setName(contact.getName());
        if(contact.getDetails() != null)
            c.setDetails(contact.getDetails());
        if(contact.getEmail() != null)
            c.setEmail(contact.getEmail());
        if(contact.getPhone() != null)
            c.setPhone(contact.getPhone());
        if(contact.getEmail() != null)
            c.setEmail(contact.getEmail());
        contactRepository.save(c);
        return c;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/ads/{id}")
    public String delete(@PathVariable String id) {
        Optional<Ads> optcontact = contactRepository.findById(id);
        Ads contact = optcontact.get();
        contactRepository.delete(contact);

        return "";
    }
}
