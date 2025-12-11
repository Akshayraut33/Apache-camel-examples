package com.hcl.dataInitializer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.dataInitializer.model.RecommendedPost;

@Repository
public interface RecommendedPostRepository extends JpaRepository<RecommendedPost, Integer> {

}
