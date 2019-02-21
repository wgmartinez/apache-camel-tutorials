package co.wgmartinez.camel.orders.invoicing;


import cxf.wsdl.wgmartinez.co.invoice_ws.AddInvoiceRequest;

public class AddInvoiceRequestBuilder {
    public AddInvoiceRequest build(String orderReference) {
        AddInvoiceRequest request = new AddInvoiceRequest();
        request.setOrderReference(orderReference);
        return request;
    }
}
