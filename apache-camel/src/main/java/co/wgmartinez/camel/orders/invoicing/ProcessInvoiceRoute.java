package co.wgmartinez.camel.orders.invoicing;

import co.wgmartinez.camel.orders.model.Order;
import cxf.wsdl.wgmartinez.co.invoice_ws.AddInvoiceRequest;
import cxf.wsdl.wgmartinez.co.invoice_ws.AddInvoiceResponse;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.apache.camel.builder.ProcessorBuilder.setHeader;

@Component
public class ProcessInvoiceRoute extends RouteBuilder {

    @Autowired
    private InvoiceClient invoiceClient;

    @Override
    public void configure() throws Exception {

//        from("direct:processOrderInvoice")
//                .process(e -> {
//                    Order order = e.getIn().getBody(Order.class);
//                    String orderReference = order.getCustomerId();
//                    e.getOut().setBody(orderReference, String.class);
//                })
//                .bean(AddInvoiceRequestBuilder.class)
//                .setHeader("operationName", constant("addInvoice"))
//                .to("cxf://http://localhost:8080?serviceClass=cxf.wsdl.wgmartinez.co.invoice_ws.InvoiceService&wsdlURL=/wsdl/invoices.wsdl");
////                .to("kafka:invoice-topic?brokers=localhost:9092&groupId=invoicechannel");

        from("direct:processOrderInvoice")
                .process(e -> {
                    AddInvoiceResponse response = invoiceClient.addArticle("1232",
                            "sdfsdf", new BigDecimal(20));

                    System.out.println("RESSSPONSEEE -> " + response.getInvoice().getAmount()
                            + " " + response.getInvoice().getCustomerReference()
                    + ", Status = " + response.getServiceStatus().getMessage() + ", " + response.getServiceStatus().getStatusCode());

                    Order order = e.getIn().getBody(Order.class);
                    order.setAdditionalProperty("Invoice Amount: ", response.getInvoice().getAmount());
                    order.setAdditionalProperty("Invoice Reference:", response.getInvoice().getInvoiceReference());
                    order.setAdditionalProperty("Invoice Service Status: ", response.getServiceStatus().getStatusCode());
                    order.setAdditionalProperty("Invoice Service Message: ", response.getServiceStatus().getMessage());

                });
    }
}
