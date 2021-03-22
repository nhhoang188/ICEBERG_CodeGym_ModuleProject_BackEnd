package com.icebergsocialnetwork.services.post;

import com.icebergsocialnetwork.model.post.Post;
import com.icebergsocialnetwork.repository.post.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepo.findAll(pageable);
    }

    @Override
    public Iterable<Post> findAll() {
        return postRepo.findAll();
    }

    @Override
    public Post findById(Long id) {
        Optional<Post> postOptional = postRepo.findById(id);
        return postOptional.orElse(null);
    }

    @Override
    public Post save(Post post) {
        return postRepo.save(post);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Post> postOptional = postRepo.findById(id);
        if (!postOptional.isPresent()) {
            return;
        }
        postRepo.deleteById(id);
    }

    @Override
    public Page<Post> findPostByUserIdOrderByCreateDateDesc(Long id, Pageable pageable) {
        return postRepo.findPostByUserIdOrderByCreateDateDesc(id, pageable);
    }
}