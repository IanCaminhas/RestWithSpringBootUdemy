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

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonService service;
	
	@GetMapping(produces = {"application/json","application/xml","application/x-yaml" })
	public List<PersonVO> findAll() {
		
		return service.findAll();
	}
	@GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml" })
	public PersonVO findById(@PathVariable(value="id") Long id) {
		
		return service.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>  delete(@PathVariable(value="id") Long id) {
		 service.delete(id);
		 return ResponseEntity.ok().build();
	}
	
	@PostMapping(produces = {"application/json","application/xml","application/x-yaml" },
			consumes = {"application/json","application/xml","application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO PersonVO) {
		return service.create(PersonVO);
	}
	
	@PutMapping(produces = {"application/json","application/xml","application/x-yaml" },
			consumes = {"application/json","application/xml","application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO PersonVO) {
		return service.update(PersonVO);
	}
	
	
	
}
