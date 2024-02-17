package com.hackerspace2024.webservice.domain.files;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface FilesRepository extends JpaRepository<Files, Long> {

    @Query("SELECT f FROM Files f WHERE f.relatedPostId = :postId")
    public Stream<Files> findAllByPostId(@Param("postId") Long postId);

    @Query("SELECT f FROM Files f WHERE f.id IN (:fileIdList)")
    public Stream<Files> findAllById(@Param("fileIdList")List<Long> fileIdList);
}
