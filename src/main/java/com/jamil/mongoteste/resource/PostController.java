package com.jamil.mongoteste.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamil.mongoteste.domain.Post;
import com.jamil.mongoteste.resource.utils.URL;
import com.jamil.mongoteste.service.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
	
	@Autowired
	private PostService service;
	
	@GetMapping
	public ResponseEntity<List<Post>>findAll(){
		
		List<Post> posts = service.findAll();
		
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Post>findById( @PathVariable String id){
		
		Post post = service.findById(id);
		
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(value="/textsearch") //    /posts/textsearch?text=
	public ResponseEntity<List<Post>>findByTitle(@RequestParam(value = "text", defaultValue = "")String txt){
		
		txt = URL.decodeParam(txt);
	    List<Post> list = service.findByTitle(txt);
		
		return ResponseEntity.ok().body(list);
	}
	
	
	@GetMapping(value="/fullSearch") //    /posts/textsearch?text=&minDate=&maxDate=
	public ResponseEntity<List<Post>>fullsearch(
			@RequestParam(value = "text", defaultValue = "")String txt,
			@RequestParam(value = "minDate", defaultValue = "")String dateMin,
			@RequestParam(value = "maxDate", defaultValue = "")String dateMax
			){
		
		txt = URL.decodeParam(txt);
		Date min = URL.converteData(dateMin, new Date(0L));
		Date max = URL.converteData(dateMax, new Date());
	    List<Post> list = service.fullSearch(txt, min, max);
		
		return ResponseEntity.ok().body(list);
	}
	

}
