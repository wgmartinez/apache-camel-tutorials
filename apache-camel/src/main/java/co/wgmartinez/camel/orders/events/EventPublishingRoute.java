package co.wgmartinez.camel.orders.events;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.google.pubsub.GooglePubsubComponent;
import org.apache.camel.component.properties.PropertiesComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class EventPublishingRoute extends RouteBuilder {

  //  @Autowired
    CamelContext context;

    //@Override
    public void configure() throws Exception {
        PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
        pc.setLocation("classpath:gcp.properties");

        // setup google pubsub component
        GooglePubsubComponent googlePubsub = PubsubUtil.createComponent();
        context.addComponent("google-pubsub", googlePubsub);

        from("direct:googlePubsubStart").routeId("DirectToGooglePubsub")
                .to("google-pubsub:kubernetes-practice-230221:myTopic").log("${headers}");

    }
}
