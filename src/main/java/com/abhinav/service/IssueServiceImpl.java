package com.abhinav.service;

import com.abhinav.config.request.IssueRequest;
import com.abhinav.model.Issue;
import com.abhinav.model.Project;
import com.abhinav.model.User;
import com.abhinav.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService{

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Override
    public Issue getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        if(issue.isEmpty()){
            throw new Exception("No issues found with issue id: "+issueId);
        }
        return issue.get();
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        List<Issue> issues = issueRepository.findByProjectId(projectId);
        if(issues.isEmpty()){
            throw new Exception("No issues yet or Invalid projectId!");
        }
        return issues;
    }

    @Override
    public Issue createIssue(IssueRequest request, User user) throws Exception {

        Project project = projectService.getProjectById(request.getProjectId());
        Issue issue = new Issue();
        issue.setTitle(request.getTitle());
        issue.setDescription(request.getDescription());
        issue.setStatus(request.getStatus());
        issue.setProjectId(request.getProjectId());
        issue.setDueDate(request.getDueDate());
        issue.setPriority(request.getPriority());

        issue.setProject(project);

        return issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {
        getIssueById(issueId);
        issueRepository.deleteById(issueId);
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        User user = userService.findUserById(userId);
        Issue issue = getIssueById(issueId);

        issue.setAssignee(user);

        return issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {
        Issue issue = getIssueById(issueId);
        issue.setStatus(status);

        return issueRepository.save(issue);
    }
}
