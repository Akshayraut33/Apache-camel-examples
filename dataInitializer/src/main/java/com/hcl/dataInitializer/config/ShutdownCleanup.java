package com.hcl.dataInitializer.config;

import com.hcl.dataInitializer.service.RecommendedPostService;

import jakarta.annotation.PreDestroy;

public class ShutdownCleanup {
      private final RecommendedPostService service;

    public ShutdownCleanup(RecommendedPostService service) {
        this.service = service;
    }

    @PreDestroy
    public void onShutdown() {
        service.deleteAll();
        System.out.println("All data deleted on shutdown.");
    }
}
