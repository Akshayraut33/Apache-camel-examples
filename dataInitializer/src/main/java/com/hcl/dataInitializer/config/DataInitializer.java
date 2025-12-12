package com.hcl.dataInitializer.config;

import com.hcl.dataInitializer.model.RecommendedPost;
import com.hcl.dataInitializer.service.RecommendedPostService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final RecommendedPostService service;

    public DataInitializer(RecommendedPostService service) {
        this.service = service;
    }

    @PostConstruct
    public void insertInitialData() {

        RecommendedPost p1 = new RecommendedPost(
                1,
                "Java Basics",
                "Learn the fundamentals of Java programming.",
                "https://example.com/java-basics.jpg");

        RecommendedPost p2 = new RecommendedPost(
                2,
                "Spring Boot Guide",
                "A complete beginner guide for Spring Boot.",
                "https://example.com/spring-boot.jpg");

        RecommendedPost p3 = new RecommendedPost(
                3,
                "Microservices Architecture",
                "Understand microservices with real examples.",
                "https://example.com/microservices.jpg");

        service.addPost(p1);
        service.addPost(p2);
        service.addPost(p3);

        System.out.println("Sample RecommendedPost data inserted at startup.");
    }
}
