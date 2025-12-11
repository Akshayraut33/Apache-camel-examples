package com.hcl.dataInitializer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hcl.dataInitializer.model.RecommendedPost;
import com.hcl.dataInitializer.repository.RecommendedPostRepository;

@Service
public class RecommendedPostService {

    @Autowired
    RecommendedPostRepository recommendedPostRepository;

    public ResponseEntity<List<RecommendedPost>> getAllPost() {

        return ResponseEntity.status(200).body(recommendedPostRepository.findAll());
    }

    public void deleteAll() {
        recommendedPostRepository.deleteAll();
    }

    public void addPost(RecommendedPost recommendedPost) {
        recommendedPostRepository.save(recommendedPost);
    }

}
