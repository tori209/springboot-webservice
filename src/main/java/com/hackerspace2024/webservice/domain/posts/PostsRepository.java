package com.hackerspace2024.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p " +
            "FROM Posts p " +
            "ORDER BY p.id DESC")
    Stream<Posts> findAllDesc();

    @Query("SELECT p " +
            "FROM Posts p " +
            "WHERE p.id = :id")
    Stream<Posts> findOneById(@Param("id") Long id);
}
