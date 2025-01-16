package com.example.mongodb_springboot.repository;

import com.example.mongodb_springboot.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    //    Using QueryMethod from Spring Boot.
    List<Post> findByTitleContainingIgnoreCase(String text);

    //    The same query and purpose of the method findByTitleContainingIgnoreCase(), but it's  a different way to do(using @Query and $regex from MongoDB).
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> findByTitle(String text);

    @Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Post> findByMultipleFields(String text, Date minDate, Date maxDate);
}