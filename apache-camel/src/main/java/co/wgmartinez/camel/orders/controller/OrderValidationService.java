package co.wgmartinez.camel.orders.controller;

import co.wgmartinez.camel.orders.converter.StringToOrder;
import co.wgmartinez.camel.orders.model.Order;
import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderValidationService {

    @Autowired
    private CamelContext camelContext;


    /* url -> /order/sync/illustrateDsl */
    public Order illustrateDsl(Order order) throws Exception {
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        Order response = null;
        try {
            response = (Order) producerTemplate.sendBody("direct:illustrateDsl", ExchangePattern.InOut, order);
        }finally {
            producerTemplate.stop();
        }

        return response;
    }

    public Order illustrateExceptionHandling(Order order) throws Exception {
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        Order response = null;

        try {
            camelContext.getTypeConverterRegistry().addTypeConverter(Order.class, String.class, new StringToOrder());
            response = (Order) producerTemplate.sendBody("direct:illustrateExceptionHandling", ExchangePattern.InOut, order);
        }finally {
            producerTemplate.stop();
        }

        return response;
    }


    /* url -> /order/sync/illustrateDsl */
    public Order splitAggregate(Order order) throws Exception {
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        Order response = null;
        try {
            response = (Order) producerTemplate.sendBody("direct:splitImmediateAggregate", ExchangePattern.InOut, order);
        }finally {
            producerTemplate.stop();
        }

        return response;
    }


    public Order multicastImmediateAggregate(Order order) throws Exception {
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        Order response = null;
        try {
            response = (Order) producerTemplate.sendBody("direct:multicastImmediateAggregate", ExchangePattern.InOut, order);
        }finally {
            producerTemplate.stop();
        }

        return response;
    }

    public Order multicastDelayedAggregate(Order order) throws Exception {
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        Order response = null;
        try {
            response = (Order) producerTemplate.sendBody("direct:multicastDelayedAggregate", ExchangePattern.InOut, order);
        }finally {
            producerTemplate.stop();
        }

        return response;
    }

}
