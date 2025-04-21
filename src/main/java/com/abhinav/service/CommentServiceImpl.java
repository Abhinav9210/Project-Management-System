package com.abhinav.service;

import com.abhinav.model.Comment;
import com.abhinav.model.Issue;
import com.abhinav.model.User;
import com.abhinav.repository.CommentRepository;
import com.abhinav.repository.IssueRepository;
import com.abhinav.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Comment createComment(Long issueId, Long userId, String comment) throws Exception {
        Optional<Issue> issueOptional = issueRepository.findById(issueId);
        Optional<User> userOptional = userRepository.findById(userId);

        if(issueOptional.isEmpty()){
            throw new Exception("issue not found with id: "+ issueId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("user not found with id: "+ userId);
        }
        Issue issue = issueOptional.get();
        User user = userOptional.get();

        Comment newComment = new Comment();

        newComment.setIssue(issue);
        newComment.setUser(user);
        newComment.setCreatedDateTime(LocalDateTime.now());
        newComment.setContent(comment);

        Comment savedComment = commentRepository.save(newComment);
        issue.getComments().add(savedComment);

        return savedComment;
    }

    @Override
    public void deleteComment(Long commentId, Long userid) throws Exception {

        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        Optional<User> userOptional = userRepository.findById(userid);


        if(commentOptional.isEmpty()){
            throw new Exception("comment not found with id: "+ commentId);
        }
        if(userOptional.isEmpty()){
            throw new Exception("user not found with id: "+ userid);
        }

        Comment comment = commentOptional.get();
        User user = userOptional.get();
        if(comment.getUser().equals(user)){
            commentRepository.delete(comment);
        }else {
            throw new Exception("User doesnot have permission to delete this comment!");
        }
    }

    @Override
    public List<Comment> findCommentByIssueId(Long issueId) {

        return commentRepository.findByIssueId(issueId);
    }
}
