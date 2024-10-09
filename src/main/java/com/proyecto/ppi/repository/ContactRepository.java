package com.proyecto.ppi.repository;

import com.proyecto.ppi.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact,Integer> {
}
