package co.wgmartinez.camel.orders.aggregator;

import co.wgmartinez.camel.orders.model.Item;
import co.wgmartinez.camel.orders.model.Order;
import co.wgmartinez.camel.orders.model.OrderDetails;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.List;

public class InventoryAggregator implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        System.out.println("InventoryAggregator 1 Item new exchange IN - " + newExchange.getIn().getBody(Item.class));

        if (oldExchange == null){
            List<Item> items = new ArrayList<>();
            items.add(newExchange.getIn().getBody(Item.class));

            Order currentOrder = new Order();
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setItems(items);
            currentOrder.setOrderDetails(orderDetails);

            // The out message of the newExchange must be set
            newExchange.getOut().setBody(currentOrder);
            return newExchange;
        }
        System.out.println("InventoryAggregator 2 Order old exchange IN - " + oldExchange.getIn().getBody(Order.class));

        // The in message is the order set in the last newExchange
        Order order = oldExchange.getIn().getBody(Order.class);
        Item item = newExchange.getIn().getBody(Item.class);
        order.getOrderDetails().getItems().add(item);
        newExchange.getOut().setBody(order);
        return newExchange;
    }
}
