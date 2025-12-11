package com.hcl.dataInitializer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.dataInitializer.model.RecommendedPost;
import com.hcl.dataInitializer.service.RecommendedPostService;

@RestController
@RequestMapping("api/instagram")
public class RecommendedPostController {

    @Autowired
    RecommendedPostService recommendedPostService;

    @GetMapping("test")
    public String testApi() {
        return "working correctly !";

    }

    @GetMapping()
    public ResponseEntity<List<RecommendedPost>> getAllPost() {
        return recommendedPostService.getAllPost();
    }

}
