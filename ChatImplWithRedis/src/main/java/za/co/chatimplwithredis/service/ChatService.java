package za.co.chatimplwithredis.service;

import org.redisson.api.RListReactive;
import org.redisson.api.RTopicReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import za.co.chatimplwithredis.config.RedissonConfig;
import java.net.URI;

@Service
public class ChatService implements WebSocketHandler {

    private final RedissonConfig redissonConfig = new RedissonConfig();

    private RedissonReactiveClient client;

    public ChatService() {
        this.client = this.redissonConfig.getReactiveClient();
    }

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        String room = getChatRoom(webSocketSession);

        RTopicReactive topic = this.client.getTopic(room, StringCodec.INSTANCE);
        RListReactive<String> list = this.client.getList("history:" + room, StringCodec.INSTANCE);

        // subscribe to client meessages
        webSocketSession.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .flatMap(msg -> list.add(msg).then(topic.publish(msg)))
                .doOnError(System.out::println)
                .doFinally(s -> System.out.println("Subscribed"+ s))
                .subscribe();

        // publish messages to client applications
        Flux<WebSocketMessage> webSocketMessageFlux = topic.getMessages(String.class).startWith(list.iterator())
                .map(webSocketSession::textMessage)
                .doOnError(System.out::println)
                .doFinally(s -> System.out.println("Publish " + s));

        return webSocketSession.send(webSocketMessageFlux);
    }

    private String getChatRoom(WebSocketSession session) {
        URI uri = session.getHandshakeInfo().getUri();
        return UriComponentsBuilder.fromUri(uri)
                .build()
                .getQueryParams()
                .toSingleValueMap()
                .getOrDefault("room", "default");
    }
}
