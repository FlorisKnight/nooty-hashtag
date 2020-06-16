package com.nooty.nootyhashtag;

import com.nooty.nootyhashtag.models.Hashtag;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HashtagRepo extends CrudRepository<Hashtag, String> {

    boolean existsByHashtag(String hashtag);

    Optional<Hashtag> findByHashtag(String hashtag);
}
