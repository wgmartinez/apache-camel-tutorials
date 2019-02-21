package co.wgmartinez.camel.orders.invoicing;

import co.wgmartinez.camel.orders.model.Order;
import cxf.wsdl.wgmartinez.co.invoice_ws.AddInvoiceResponse;
import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderInvoiceProcess {

    @Autowired
    CamelContext context;

    public Order process(Order order) throws Exception {
        ProducerTemplate producerTemplate = context.createProducerTemplate();
        Order response = null;
        try {
            response = (Order) producerTemplate.sendBody("direct:processOrderInvoice", ExchangePattern.InOut, order);
        }finally {
            producerTemplate.stop();
        }

        return response;
    }
}
