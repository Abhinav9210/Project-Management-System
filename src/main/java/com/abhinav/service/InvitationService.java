package com.abhinav.service;

import com.abhinav.model.Invitation;
import jakarta.mail.MessagingException;

public interface InvitationService {

     void sendInvitation(String email, Long projectId) throws MessagingException;

     Invitation acceptInvitation(String token, Long userId) throws Exception;

     String getTokenByUserMail(String userEmail);

     void deleteToken(String token);
}
