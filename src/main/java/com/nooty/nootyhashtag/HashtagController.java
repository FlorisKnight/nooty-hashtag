package com.nooty.nootyhashtag;

import com.nooty.nootyhashtag.models.Hashtag;
import com.nooty.nootyhashtag.models.HashtagNoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/hashtag")
public class HashtagController {
    @Autowired
    HashtagRepo hashtagRepo;

    @Autowired
    HashtagNootRepo hashtagNootRepo;

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity getAll() {
        List<Hashtag> hashtags = new ArrayList<Hashtag>();
        this.hashtagRepo.findAll().forEach(h -> {
            hashtags.add(h);
        });

        return ResponseEntity.ok(hashtags);
    }

    @GetMapping(path = "/id/{id}", produces = "application/json")
    public ResponseEntity getNootIsFromHashtagId(@PathVariable String id) {
        List<String> hashtagNoots = new ArrayList<String>();
        this.hashtagNootRepo.findByHashtagId(id).forEach(h -> {
            hashtagNoots.add(h.getNootId());
        });

        return ResponseEntity.ok(hashtagNoots);
    }

    @GetMapping(path = "/{hashtag}", produces = "application/json")
    public ResponseEntity getNootIsFromHashtag(@PathVariable String hashtag) {
        String hashtagWithHashtag = "#" + hashtag;
        List<String> hashtagNoots = new ArrayList<String>();
        Optional<Hashtag> hashtagOptional = this.hashtagRepo.findByHashtag(hashtagWithHashtag);
        if (hashtagOptional.isPresent()) {
            Hashtag h = hashtagOptional.get();
            this.hashtagNootRepo.findByHashtagId(h.getId()).forEach(hastagNoot -> {
                hashtagNoots.add(hastagNoot.getNootId());
            });
        }

        return ResponseEntity.ok(hashtagNoots);
    }

    @GetMapping(path = "/trends", produces = "application/json")
    public ResponseEntity getTrends() {
        //TODO find out whats trending
        List<Hashtag> hashtags = new ArrayList<Hashtag>();
        this.hashtagRepo.findAll().forEach(h -> {
            hashtags.add(h);
        });

        return ResponseEntity.ok(hashtags);
    }
}
