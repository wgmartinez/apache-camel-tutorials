package co.wgmartinez.camel.orders.aggregator;

import co.wgmartinez.camel.orders.model.Item;
import co.wgmartinez.camel.orders.model.Specification;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class SpecificationAggregator implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange originalExchange, Exchange newExchange) {
        Item item = originalExchange.getIn().getBody(Item.class);

        System.out.println("SpecificationAggregator -> original item " + originalExchange.getIn().getBody(Item.class));

//        System.out.println("SpecificationAggregator -> item new 1 " + newExchange.getIn().getBody(Item.class));
//        System.out.println("SpecificationAggregator -> item new 2 " + newExchange.getMessage().getBody(Item.class));
//        System.out.println("SpecificationAggregator -> item new 3 " + newExchange.getOut().getBody(Item.class));

        System.out.println("SpecificationAggregator -> item new 4 " + newExchange.getIn().getBody(Specification.class));
//        System.out.println("SpecificationAggregator -> item new 5 " + newExchange.getMessage().getBody(Specification.class));
//        System.out.println("SpecificationAggregator -> item new 6 " + newExchange.getOut().getBody(Specification.class));
        item.setSpecification(newExchange.getIn().getBody(Specification.class));
        return originalExchange;
    }
}
