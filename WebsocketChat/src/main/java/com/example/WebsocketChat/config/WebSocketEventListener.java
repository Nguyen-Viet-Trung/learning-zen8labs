package com.example.WebsocketChat.config;

import org.apache.tomcat.util.threads.StopPooledThreadException;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.WebsocketChat.entity.ChatMessage;
import com.example.WebsocketChat.entity.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j // ADD private static final Logger log
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;
    @EventListener
    public void handleWebSocketDisconnectListener(
        SessionDisconnectEvent event
    ){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage()); //allowing access to the headers of the STOMP (Simple Text-Oriented Messaging Protocol) message.
        String username = (String)headerAccessor.getSessionAttributes().get("username"); //extract username
        if(username != null){
            log.info(username + " Disconnected");
            var chatMessage = ChatMessage.builder()
            .type(MessageType.LEAVE)
            .sender(username)
            .build();
            messageTemplate.convertAndSend("/topic/public", chatMessage);
            
        }
    }
}
