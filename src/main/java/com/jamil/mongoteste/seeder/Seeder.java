package com.jamil.mongoteste.seeder;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jamil.mongoteste.domain.Post;
import com.jamil.mongoteste.domain.User;
import com.jamil.mongoteste.dto.AuthorDTO;
import com.jamil.mongoteste.dto.CommentDTO;
import com.jamil.mongoteste.repository.PostRepository;
import com.jamil.mongoteste.repository.UserRepository;

@Configuration
public class Seeder implements CommandLineRunner {

	@Autowired
	private UserRepository repoUser;
	
	@Autowired
	private PostRepository repoPost;
	
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		repoUser.deleteAll();
		repoPost.deleteAll();
	
		
		User user1 = new User("Jon Black","jon@gmail.com");
		User user2 = new User("Mary Green","mary@gmail.com");
		User user3 = new User("Scot Blue","scot@gmail.com");
		User user4 = new User("Luna Violet","luna@gmail.com");
		User user5 = new User("Brad Red","brad@gmail.com");
		
		repoUser.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
		
		Post post1 = new Post(null, sdf.parse("21/03/2019"), "Post 1", "blablablablabla", new AuthorDTO(user1));
		Post post2 = new Post(null, sdf.parse("21/03/2019"), "Post 2", "pipipopo", new AuthorDTO(user1));

		CommentDTO comment1 = new CommentDTO("blablabla?", sdf.parse("21/03/2019"), new AuthorDTO(user2));
		CommentDTO comment2 = new CommentDTO("blabla!", sdf.parse("22/03/2019"), new AuthorDTO(user2));
	
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		
		
		repoPost.saveAll(Arrays.asList(post1,post2));
		
		user1.adiciona(post1);
		user1.adiciona(post2);
		repoUser.save(user1);
		
	}

}
