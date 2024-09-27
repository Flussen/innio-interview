package com.innio.api.adapters.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innio.api.adapters.request.LinkRequest;
import com.innio.api.adapters.response.StandardResponse;
import com.innio.api.service.ShortUrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/shortlink")
public class ShortUrlController {

    @Autowired
    ShortUrlService shortUrlService;
    
    @GetMapping("/get-link-by-id/{id}")
    public ResponseEntity<StandardResponse<?>> getLinkByID(@PathVariable String id) {
        return shortUrlService.getLinkById(id);
    }

    @GetMapping("/get-all-links")
    public ResponseEntity<StandardResponse<?>> getAllLinks() {
        return shortUrlService.getAllLinks();
    }

    @PostMapping("/create-new-link")
    public ResponseEntity<StandardResponse<?>> shortALlink(@RequestBody LinkRequest link) {
        return shortUrlService.shortALink(link);
    }

}
