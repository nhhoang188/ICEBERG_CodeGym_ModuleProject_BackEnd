package com.icebergsocialnetwork.repository.post;

import com.icebergsocialnetwork.model.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface PostRepo extends PagingAndSortingRepository<Post, Long> {
}
