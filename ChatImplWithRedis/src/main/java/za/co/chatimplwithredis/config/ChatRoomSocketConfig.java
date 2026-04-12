package za.co.chatimplwithredis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import za.co.chatimplwithredis.service.ChatService;
import java.util.Map;

@Configuration
public class ChatRoomSocketConfig {

    @Autowired
    private ChatService chatService;

    @Bean
    public HandlerMapping handlerMapping() {
        Map<String, WebSocketHandler> stringChatServiceMap = Map.of("/chat", chatService);
        return new SimpleUrlHandlerMapping(stringChatServiceMap, -1);
    }
}
