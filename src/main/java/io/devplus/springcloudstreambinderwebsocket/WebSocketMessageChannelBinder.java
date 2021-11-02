//By Guomin Huang @17/16/2021
package io.devplus.springcloudstreambinderwebsocket;

import io.devplus.springcloudstreambinderwebsocket.adapters.InboundMessageProducer;
import io.devplus.springcloudstreambinderwebsocket.adapters.OutboundMessageHandler;
import io.devplus.springcloudstreambinderwebsocket.operation.WebSocketOperation;
import io.devplus.springcloudstreambinderwebsocket.provisioning.WebSocketChannelProvisioner;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.cloud.stream.binder.AbstractMessageChannelBinder;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.core.MessageProducer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.web.servlet.HandlerMapping;

public class WebSocketMessageChannelBinder extends AbstractMessageChannelBinder<ConsumerProperties, ProducerProperties, WebSocketChannelProvisioner> {

    //private WebSocketOperation webSocketOperation;

    public WebSocketMessageChannelBinder(String[] headersToEmbed, WebSocketChannelProvisioner provisioningProvider/*, WebSocketOperation webSocketOperation*/) {
        super(headersToEmbed, provisioningProvider);
        //this.webSocketOperation = webSocketOperation;
    }

    //@Override
    protected MessageHandler createProducerMessageHandler(ProducerDestination destination, ProducerProperties producerProperties, MessageChannel errorChannel) throws Exception {
        return new OutboundMessageHandler();
    }

    //@Override
    protected MessageProducer createConsumerEndpoint(ConsumerDestination destination, String group, ConsumerProperties properties) throws Exception {
        AbstractApplicationContext appCtx = this.getApplicationContext();
        WebSocketOperation operation = null;
        if (appCtx.containsBean("webSocketHandlerMapping")) {
            HandlerMapping handlerMapping = (HandlerMapping)appCtx.getBean("webSocketHandlerMapping");
            operation = (WebSocketOperation)appCtx.getBean("webSocketOperation");
        }
        return new InboundMessageProducer(operation);
    }
}
