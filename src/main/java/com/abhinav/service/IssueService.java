package com.abhinav.service;

import com.abhinav.config.request.IssueRequest;
import com.abhinav.model.Issue;
import com.abhinav.model.User;

import java.util.List;

public interface IssueService {

    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest request, User user) throws Exception;

    void deleteIssue(Long issueId , Long userId) throws Exception;

    Issue addUserToIssue(Long issueId,Long userId) throws Exception;

    Issue updateStatus(Long issueId,String status) throws Exception;
}
