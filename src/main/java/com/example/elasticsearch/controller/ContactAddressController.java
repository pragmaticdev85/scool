package com.example.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.elasticsearch.model.ContactAddress;
import com.example.elasticsearch.repository.ContactAddressRepository;

import java.util.Optional;
@RestController
@RequestMapping("/api/contact-addresses")
public class ContactAddressController {
    @Autowired
    private ContactAddressRepository repository;

    @PostMapping
    public ContactAddress create(@RequestBody ContactAddress ContactAddress) {
        return repository.save(ContactAddress);
    }
    @GetMapping("/{id}")
    public Optional<ContactAddress> findById(@PathVariable String id) {
        return repository.findById(id);
    }

    @GetMapping
    public Iterable<ContactAddress> findAll() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public ContactAddress update(@PathVariable String id, @RequestBody ContactAddress ContactAddress) {
        ContactAddress.setId(id);
        return repository.save(ContactAddress);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAll(){
        repository.deleteAll();
    }
}
