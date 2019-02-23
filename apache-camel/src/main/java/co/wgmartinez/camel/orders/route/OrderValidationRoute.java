package co.wgmartinez.camel.orders.route;

import co.wgmartinez.camel.orders.aggregator.InventoryAggregator;
import co.wgmartinez.camel.orders.aggregator.SpecificationAggregator;
import co.wgmartinez.camel.orders.model.CustomerDetails;
import co.wgmartinez.camel.orders.model.Order;
import co.wgmartinez.camel.orders.service.SpecificationService;
import co.wgmartinez.camel.orders.service.WarehouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ConnectException;

@Component
public class OrderValidationRoute extends RouteBuilder {

    private Order order;

    @Override
    public void configure() throws Exception {
        onException(ConnectException.class)
                .handled(true)
                .maximumRedeliveries(2)
                .maximumRedeliveryDelay(5000)
                .process(e -> {
                    if (order == null) {
                        order = new Order();
                    }
                    order.setAdditionalProperty("ConnectionException", "Could not connect to the remote server. Please try again later");
                    e.getOut().setBody(order);
                    e.getMessage().setBody(order);
                })
                .end();


        from("direct:illustrateDsl")
                .process(e -> {
                    order = e.getIn().getBody(Order.class);
                    e.getOut().setBody(order);
                })
                .wireTap("direct:googlePubsubStart")
                .to("direct:customerDetails")
                .end();


        from("direct:splitImmediateAggregate")
                .process(e -> {
                    order = e.getIn().getBody(Order.class);
                    e.getOut().setBody(order);
                })
                .to("direct:itemDetailsImmediateAggregate")
                .end();


        from("direct:multicastImmediateAggregate")
                .process(e -> {
                    order = e.getIn().getBody(Order.class);
                    e.getOut().setBody(order);
                })
                .multicast()
                .parallelProcessing()
                .to("direct:customerDetails", "direct:itemDetailsImmediateAggregate")
                .end();


        from("direct:multicastDelayedAggregate")
                .process(e -> {
                    order = e.getIn().getBody(Order.class);
                    e.getOut().setBody(order);
                })
                .multicast()
                .parallelProcessing()
                .to("direct:customerDetails", "direct:itemDetailsDelayedAggregate")
                .end();


        from("direct:customerDetails")
                .process(e -> {
                    order = e.getIn().getBody(Order.class);
                    e.getOut().setBody(order.getCustomerId());
                })
                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .setHeader(Exchange.HTTP_QUERY, simple("id=${body}"))
                .to("http://localhost:21003/api/customer")
                .convertBodyTo(String.class)
                .process(e -> {
                    final ObjectMapper mapper = new ObjectMapper();
                    final String val = e.getIn().getBody(String.class);
                    CustomerDetails customerDetails = null;
                    try {
                        customerDetails = mapper.readValue(val, CustomerDetails.class);
                    } catch (IOException exc) {
                        throw exc;
                    }
                    order.setCustomerDetails(customerDetails);
                    e.getOut().setBody(order, Order.class);
                })
                .end();


        from("direct:itemDetailsImmediateAggregate")
                .split(simple("${body.orderDetails.items}"), new InventoryAggregator())
                .bean(WarehouseService.class, "checkInventory")
                .enrich("direct:enrichWithSpecification", new SpecificationAggregator())
                .end()
                .to("direct:aggregateCustomerDetailsAndItemDetails")
                .end();


        from("direct:itemDetailsDelayedAggregate")
                .split(simple("${body.orderDetails.items}"))
                .bean(WarehouseService.class, "checkInventory")
                .enrich("direct:enrichWithSpecification", new SpecificationAggregator())
                .aggregate(constant(true), new InventoryAggregator())
                .completionTimeout(100)
                .completionTimeoutCheckerInterval(100)
                .to("direct:aggregateCustomerDetailsAndItemDetails")
                .end();


        from("direct:enrichWithSpecification")
                .bean(SpecificationService.class, "getSpecification")
                .end();


        from("direct:aggregateCustomerDetailsAndItemDetails")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        Order o = exchange.getMessage().getBody(Order.class);

                        System.out.println("FINISH PROCESSOR Exchange message -> " + o);
                        System.out.println("FINISH PROCESSOR LOCAL VAR Order message -> " + order);

                        order.setOrderDetails(o.getOrderDetails());
                        exchange.getOut().setBody(order, Order.class);
                    }
                })
                .end();

        from("direct:googlePubsubStart")
                .to("google-pubsub:kubernetes-practice-230221:myTopic");

    }

}
