package com.jamil.mongoteste.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.jamil.mongoteste.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	@Query("{'title:'{ $regex: ?0, $options:'i'} }")
	List<Post>searchTitle(String txt);
	
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	
	@Query("  { $and: [ "
			+ "			{ {date: {$gt: ?1} } },"
			+ "    		{ {date: {$lt: ?2} } },"
			+ "     	{ $or: [ "
			+ "					 {'title:'{ $regex: ?0, $options:'i'} },"
			+ "				 	 {'body:'{ $regex: ?0, $options:'i'} },"
			+ "				 	 {'comments.text:'{ $regex: ?0, $options:'i'} } "
			+ "				   ] "
			+ "			}"
			+ "       ] "
			+ "}")
	List<Post>findText(String text, Date dataMin, Date dataMax);
}
