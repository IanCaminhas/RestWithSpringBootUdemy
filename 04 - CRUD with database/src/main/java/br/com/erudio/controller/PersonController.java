package br.com.erudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.model.Person;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@GetMapping
	public List<Person> findAll() {
		
		return service.findAll();
	}
	@GetMapping ("/{id}")
	public Person findById(@PathVariable(value="id") Long id) {
		
		return service.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>  delete(@PathVariable(value="id") Long id) {
		 service.delete(id);
		 return ResponseEntity.ok().build();
	}
	
	@PostMapping
	public Person create(@RequestBody Person person) {
		return service.create(person);
	}
	
	@PutMapping
	public Person update(@RequestBody Person person) {
		return service.update(person);
	}
	
	
	
}
