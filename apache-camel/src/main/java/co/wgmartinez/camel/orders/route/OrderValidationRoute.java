package co.wgmartinez.camel.orders.route;

import co.wgmartinez.camel.orders.aggregator.InventoryAggregator;
import co.wgmartinez.camel.orders.aggregator.SpecificationAggregator;
import co.wgmartinez.camel.orders.controller.MyException;
import co.wgmartinez.camel.orders.model.CustomerDetails;
import co.wgmartinez.camel.orders.model.Order;
import co.wgmartinez.camel.orders.service.SpecificationService;
import co.wgmartinez.camel.orders.service.WarehouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;

@Component
public class OrderValidationRoute extends RouteBuilder {

    private Order order;

    @Override
    public void configure() throws Exception {
//        onException(Exception.class)
//                .handled(true)
//                .maximumRedeliveries(-1)
//                .maximumRedeliveryDelay(10000)
//                .process(e -> {
//                    if (order == null) {
//                        order = new Order();
//                    }
//                    order.setAdditionalProperty("Exception", "Something went wrong, please try again laterr");
//                    e.getOut().setBody(order);
//                    e.getMessage().setBody(order);
//                })
//                .end();


        from("direct:illustrateDsl")
                .process(e -> {
                    order = e.getIn().getBody(Order.class);
                    e.getOut().setBody(order);
                })
//                .wireTap("direct:googlePubsubStart")
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
                .to("direct:customerDetailsWithExceptionProcessing", "direct:itemDetailsDelayedAggregate")
                .end();


        from("direct:illustrateExceptionHandling")
                .process(e -> {
                    order = e.getIn().getBody(Order.class);
                    e.getOut().setBody(order);
                })
                .to("direct:customerDetailsWithExceptionProcessing")
                .end();

        from("direct:illustrateDefaultExceptionHandling")
                .process(e -> {
                    try {
                        order = e.getIn().getBody(Order.class);
                    }catch (Exception exc){
                        order.setAdditionalProperty("MyException1", exc.getMessage());
                    }finally{
                        e.getOut().setBody(order);
                    }
                })
                .to("direct:customerDetailsWithExceptionProcessing")
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



        from("direct:customerDetailsWithExceptionProcessing")
                .process(e -> {
                    try {
                        order = e.getIn().getBody(Order.class);
                    } catch (Exception exc){
                        order.setAdditionalProperty("ExceptioncustomerDetailsWithExceptionProcessing", "customerDetailsWithExceptionProcessing error");
                        e.getOut().setFault(true);
                    }finally {
                        e.getOut().setBody(order.getCustomerId());
                    }
                })
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        try {
                            String customerId = exchange.getMessage().getBody(String.class);
                            StringBuilder url = new StringBuilder();
                            url.append("http://localhost:21003/api/customer?id=");
                            url.append(customerId);

                            HttpClient client = HttpClientBuilder.create().build();
                            HttpGet request = new HttpGet(url.toString());

                            HttpResponse response = client.execute(request);

                            BufferedReader rd = new BufferedReader(
                                    new InputStreamReader(response.getEntity().getContent()));

                            StringBuffer result = new StringBuffer();
                            String line = "";
                            while ((line = rd.readLine()) != null) {
                                result.append(line);
                            }

                            exchange.getOut().setBody(result.toString());

                        }catch (Exception e){
                            final ObjectMapper mapper = new ObjectMapper();
                            CustomerDetails details = new CustomerDetails();
                            details.setAdditionalProperty("ConnectionException", "The Customer Details API is currently not available");
                            exchange.getOut().setBody(mapper.writeValueAsString(details));
                            exchange.getOut().setFault(true);
                        }

                    }
                })
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

//        from("direct:googlePubsubStart")
//                .to("google-pubsub:kubernetes-practice-230221:myTopic");

    }

}
