package com.proyecto.ppi.controller;

import com.proyecto.ppi.entity.Contact;
import com.proyecto.ppi.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@RequestMapping("/api/contacts")
@RestController


public class ContactController {

    private final ContactService contactservice;


    @GetMapping("/list")
    Iterable<Contact>list(){
        return contactservice.findAll();
    }
    @GetMapping("/{id}")
    public Contact get(@PathVariable Integer id){
        return contactservice.findById(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Contact create(@RequestBody  Contact contact){
        contact.setCreateAt(LocalDateTime.now());
        return contactservice.create(contact);
    }
    @PutMapping("/{id}")
    public Contact update(@PathVariable Integer id,
                          @RequestBody  Contact form){
        return contactservice.update(id,form);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){

        contactservice.delete(id);
    }


}
