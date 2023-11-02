package com.example.coffee2.reponsitory;

import com.example.coffee2.entity.PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PostsRepository extends JpaRepository<PostsEntity, Long> {
    @Query(
            value = "SELECT * FROM posts",
            nativeQuery = true
    )
    List<PostsEntity> findAllPosts();

    @Query(
            value = "select e.title from posts e where e.title = :title",
            nativeQuery = true
    )
    List<String> findByTitle(@RequestParam String title);

    @Query(
            value = "select e.id from posts e where e.id = :id",
            nativeQuery = true
    )
    PostsEntity getPostsId(@RequestParam Long id);


//    List<String> findAllByName(@RequestParam String name);



}
