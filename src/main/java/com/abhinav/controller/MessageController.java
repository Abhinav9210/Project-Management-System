package com.abhinav.controller;

import com.abhinav.config.request.CreateMessageRequest;
import com.abhinav.model.Chat;
import com.abhinav.model.Message;
import com.abhinav.model.User;
import com.abhinav.service.MessageService;
import com.abhinav.service.ProjectService;
import com.abhinav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request) throws Exception {
        User user = userService.findUserById(request.getSenderId());
        Chat chat = projectService.getProjectById(request.getProjectId()).getChat();
        if(chat == null){
            throw new Exception("chats not found!");
        }
        Message sentMessage = messageService.sendMessage(request.getSenderId(), request.getProjectId(), request.getContent());
        return ResponseEntity.ok(sentMessage);

    }
    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws Exception {

        List<Message> messages = messageService.getMesssagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }
}
