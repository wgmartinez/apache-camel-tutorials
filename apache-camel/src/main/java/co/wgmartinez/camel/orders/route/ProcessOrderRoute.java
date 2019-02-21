package co.wgmartinez.camel.orders.route;

import co.wgmartinez.camel.orders.model.Order;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProcessOrderRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:processOrderStart")
                .to("direct:processOrder");


        from("direct:processOrder")
                .log("Order Processed");


    }
}
