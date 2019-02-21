package co.wgmartinez.camel.orders.invoicing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class WSConfigClient {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cxf.wsdl.wgmartinez.co.invoice_ws");
        return marshaller;
    }

    @Bean
    public InvoiceClient invoiceClient(Jaxb2Marshaller marshaller) {
        InvoiceClient client = new InvoiceClient();
        client.setDefaultUri("http://localhost:8080/soapws/invoices.wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}

