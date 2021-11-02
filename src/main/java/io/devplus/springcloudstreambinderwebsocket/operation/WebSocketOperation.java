package io.devplus.springcloudstreambinderwebsocket.operation;

import java.util.function.Consumer;
import org.springframework.messaging.Message;

public interface WebSocketOperation {
    public void subscribe(Consumer<Message<?>> consumer);
}
