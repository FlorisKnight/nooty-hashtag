package com.nooty.nootyhashtag;

import com.nooty.nootyhashtag.models.HashtagNoot;
import org.springframework.data.repository.CrudRepository;

public interface HashtagNootRepo extends CrudRepository<HashtagNoot, String> {

    Iterable<HashtagNoot> findByHashtagId(String hashtagId);

    boolean existsByHashtagIdAndNootId(String hashtagId, String nootId);

    boolean existsByNootId(String nootId);

    boolean deleteByNootId(String nootId);
}
