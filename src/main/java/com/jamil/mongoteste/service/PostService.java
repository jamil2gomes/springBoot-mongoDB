package com.jamil.mongoteste.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamil.mongoteste.domain.Post;
import com.jamil.mongoteste.repository.PostRepository;
import com.jamil.mongoteste.service.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repoPost;
	
	
	public List<Post> findAll(){
		return repoPost.findAll();
	}
	
	public Post findById(String id) {
		
		Post post = repoPost.findById(id).orElse(null);
		
		if(post == null) throw new ObjectNotFoundException("Post não encontrado no banco de dados.");
		
		return post;
		
	}
	
	public List<Post> findByTitle(String txt){
		return repoPost.findByTitleContainingIgnoreCase(txt);
	}
	
	public List<Post> fullSearch(String txt, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime()+ 24 * 60 * 60 * 1000 );
		return repoPost.findText(txt, minDate, maxDate);
	}
}
