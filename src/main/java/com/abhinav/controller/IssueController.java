package com.abhinav.controller;

import com.abhinav.config.DTO.IssueDTO;
import com.abhinav.config.request.IssueRequest;
import com.abhinav.model.Issue;
import com.abhinav.model.User;
import com.abhinav.response.AuthResponse;
import com.abhinav.response.MessageResponse;
import com.abhinav.service.IssueService;
import com.abhinav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {

        return ResponseEntity.ok(issueService.getIssueById(issueId));

    }
    @GetMapping("/project/{issueId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long issueId) throws Exception {

        return ResponseEntity.ok(issueService.getIssueByProjectId(issueId));
    }
    @PostMapping
    public ResponseEntity<IssueDTO> createIssue(
            @RequestBody IssueRequest issueRequest,@RequestHeader("Authorization") String token
            ) throws Exception {
        User tokenUser = userService.findUserProfileByJwt(token);
        User user = userService.findUserById(tokenUser.getId());

        Issue createdIssue = issueService.createIssue(issueRequest,tokenUser);

        IssueDTO issueDTO = new IssueDTO();

        issueDTO.setDescription(createdIssue.getDescription());
        issueDTO.setDueDate(createdIssue.getDueDate());
        issueDTO.setId(createdIssue.getId());
        issueDTO.setPriority(createdIssue.getPriority());
        issueDTO.setProject(createdIssue.getProject());
        issueDTO.setProjectId(createdIssue.getProjectId());
        issueDTO.setTags(createdIssue.getTags());
        issueDTO.setAssignee(createdIssue.getAssignee());
        issueDTO.setTitle(createdIssue.getTitle());
        issueDTO.setStatus(createdIssue.getStatus());

        return ResponseEntity.ok(issueDTO);
    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteissue(@PathVariable Long issueId,
                                                    @RequestHeader("Authorization")String token) throws Exception {
        User user = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId,user.getId());

        MessageResponse res = new MessageResponse();
        res.setMessage("Issue deleted");
        return ResponseEntity.ok(res);

    }
    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId
            ,@PathVariable Long userId) throws Exception {
        Issue issue = issueService.addUserToIssue(issueId,userId);

        return ResponseEntity.ok(issue);

    }

    @GetMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable String status,
                                                   @PathVariable Long issueId) throws Exception {

        Issue issue = issueService.updateStatus(issueId,status);

    }
}
