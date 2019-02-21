package co.wgmartinez.camel.orders.invoicing;

import cxf.wsdl.wgmartinez.co.invoice_ws.AddInvoiceRequest;
import cxf.wsdl.wgmartinez.co.invoice_ws.AddInvoiceResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.math.BigDecimal;

public class InvoiceClient extends WebServiceGatewaySupport {

    public AddInvoiceResponse addArticle(String orderReference, String customerRef, BigDecimal amount) {
        AddInvoiceRequest request = new AddInvoiceRequest();
        request.setOrderReference(orderReference);
        request.setCustomerReference(customerRef);
        request.setAmount(amount);
        AddInvoiceResponse response = (AddInvoiceResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:8080/soapws/addInvoice"));
        return response;
    }
}
