package br.com.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rest.api.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}