package com.jamil.mongoteste.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jamil.mongoteste.domain.User;
import com.jamil.mongoteste.dto.UserDTO;
import com.jamil.mongoteste.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>>findAll(){
		
		List<User>list = service.findAll();
		
		List<UserDTO> usersDTO = list.stream()
									 .map(x->new UserDTO(x))
									 .collect(Collectors.toList());
		
		return ResponseEntity.ok().body(usersDTO);
	}
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO>findById( @PathVariable String id){
		
		User user = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping
	public ResponseEntity<Void>insert(@RequestBody UserDTO obj){
		User user = service.fromDTO(obj);
		user = service.insert(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")
											 .buildAndExpand(user.getId())
											 .toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<UserDTO>delete( @PathVariable String id){
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void>update(@RequestBody UserDTO obj, @PathVariable String id){
		User user = service.fromDTO(obj);
		user.setId(id);
		user = service.update(user);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
}
