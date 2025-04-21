package com.abhinav.service;

import com.abhinav.model.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long issueId,Long userId ,String comment) throws Exception;

    void deleteComment(Long commentId, Long userid) throws Exception;

    List<Comment> findCommentByIssueId(Long issueId);

}
