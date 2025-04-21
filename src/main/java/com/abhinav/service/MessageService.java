package com.abhinav.service;

import com.abhinav.model.Chat;
import com.abhinav.model.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId, Long projectId, String content) throws Exception;
    List<Message> getMesssagesByProjectId(Long projectId) throws Exception;


}
