package com.example.model.repository;

import com.example.model.domain.Person;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>  {
}
