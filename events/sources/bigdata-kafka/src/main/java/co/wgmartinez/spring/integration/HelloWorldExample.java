package co.wgmartinez.spring.integration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

public class HelloWorldExample {
    public static void main(String args[]) {
        String cfg = "si-context.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(cfg);
        MessageChannel channel = context.getBean("names", MessageChannel.class);
        Message<String> message = MessageBuilder.withPayload("World").build();
        channel.send(message);
    }
}
