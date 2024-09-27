package com.innio.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.innio.api.adapters.repository.LinkRepository;
import com.innio.api.adapters.request.LinkRequest;
import com.innio.api.adapters.response.StandardResponse;
import com.innio.api.domain.LinkEntity;

@Service
public class ShortUrlService {

    @Autowired
    LinkRepository linkRepository;

    public ResponseEntity<StandardResponse<?>> shortALink(LinkRequest request) {
        try {
            Optional<LinkEntity> linkedGetted = linkRepository.findByLink(request.getLink());
            if(linkedGetted.isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "this link already exists!");
            }

            LinkEntity linkEntity = new LinkEntity(request.getLink());

            linkEntity = linkRepository.save(linkEntity);

            StandardResponse<LinkEntity> response = new StandardResponse<LinkEntity>("created", linkEntity);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (ResponseStatusException e ) {
            StandardResponse<String> response = new StandardResponse<String>("ok", e.getReason());
            return new ResponseEntity<>(response, e.getStatusCode());
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<StandardResponse<?>> getLinkById(String id) {
        try {
           Optional<LinkEntity> linkEntity = linkRepository.findById(id);
            linkEntity.orElseThrow( () -> {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "link not found");
            });

            StandardResponse<LinkEntity> response = new StandardResponse<LinkEntity>("ok", linkEntity.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (ResponseStatusException e) {
            StandardResponse<String> response = new StandardResponse<String>("ok", e.getReason());
            return new ResponseEntity<>(response, e.getStatusCode());
        }
         catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<StandardResponse<?>> getAllLinks() {
        try {
            List<LinkEntity> linksEntities = linkRepository.findAll();
            if(linksEntities.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "link not found");
            }
        
            StandardResponse<List<LinkEntity>> response = new StandardResponse<>("ok", linksEntities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
