package com.icebergsocialnetwork.services.comment;

import com.icebergsocialnetwork.model.comment.Comment;
import com.icebergsocialnetwork.model.post.Post;
import com.icebergsocialnetwork.services.IGenericService;

import java.util.*;

public interface CommentService extends IGenericService<Comment> {
    Iterable<Comment> findAllCommentByPostId(Long postId);

    int deleteLoveComment(Long commentId);

    int deleteComment(Long commentId);

    List<Long> findUserId(Long commentId);

    int updateComment(String content, Long commentId, Long postId);

    String findNameUser(Long userId);

    Comment findCommentByCommentId(Long commentId);
}
