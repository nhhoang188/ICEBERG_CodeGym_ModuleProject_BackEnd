package com.icebergsocialnetwork.services.comment;

import com.icebergsocialnetwork.model.comment.Comment;
import com.icebergsocialnetwork.model.post.Post;
import com.icebergsocialnetwork.services.IGenericService;

public interface CommentService extends IGenericService<Comment> {
    Iterable<Comment> findAllCommentByPostId(Long postId);
}
