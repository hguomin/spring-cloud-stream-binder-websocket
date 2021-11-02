//By Guomin Huang @17/10/2021
package io.devplus.springcloudstreambinderwebsocket.adapters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.handler.AbstractMessageProducingHandler;
import org.springframework.messaging.Message;

/**
 * This class handle message from application code, then send it to web socket channel
 * @author Guomin Huang
 */
public class OutboundMessageHandler extends AbstractMessageProducingHandler {
    private final Logger log = LoggerFactory.getLogger(OutboundMessageHandler.class);
    @Override
    protected void handleMessageInternal(Message<?> message) {
        log.info(message.toString());
    }
}
