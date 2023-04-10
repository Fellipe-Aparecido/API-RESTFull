package br.com.rest.api.services;

import br.com.rest.api.controller.PersonController;
import br.com.rest.api.converter.DozerConverter;
import br.com.rest.api.data.model.Person;
import br.com.rest.api.data.vo.v1.PersonVO;
import br.com.rest.api.exception.RequiredObjectIsNullException;
import br.com.rest.api.exception.ResourceNotFoundException;
import br.com.rest.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository repository;
		
	public PersonVO create(PersonVO person) {
		if (person == null) throw new RequiredObjectIsNullException();
		var entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class)
				.findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public List<PersonVO> findAll() {
		var persons = DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}	
	
	public PersonVO findById(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		var vo = DozerConverter.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
		
	public PersonVO update(PersonVO person) {
		if (person == null) throw new RequiredObjectIsNullException();
		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}	
	
	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
