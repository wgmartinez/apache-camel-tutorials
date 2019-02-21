package co.wgmartinez.soap.endpoints;

import co.wgmartinez.wsdl.AddInvoiceRequest;
import co.wgmartinez.wsdl.AddInvoiceResponse;
import co.wgmartinez.wsdl.Invoice;
import co.wgmartinez.wsdl.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import co.wgmartinez.soap.service.InvoiceService;

import java.math.BigDecimal;

@Endpoint
public class InvoiceEndpoint {
	private static final String NAMESPACE_URI = "http://co.wgmartinez.wsdl/invoice-ws/";

	@Autowired
	private InvoiceService invoiceService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addInvoiceRequest")
	@ResponsePayload
	public AddInvoiceResponse addInvoice(@RequestPayload AddInvoiceRequest request) {
		AddInvoiceResponse response = new AddInvoiceResponse();
    	ServiceStatus serviceStatus = new ServiceStatus();
		Invoice invoice = new Invoice();
		invoice.setAmount(new BigDecimal(1123.45));
		invoice.setCustomerReference(request.getCustomerReference());
		invoice.setOrderReference(request.getOrderReference());
		invoice.setInvoiceReference("INV1234");

		serviceStatus.setMessage("Successfully processed invoice " + invoice.getInvoiceReference());
		serviceStatus.setStatusCode("SUCCESS");
		response.setServiceStatus(serviceStatus);

		response.setInvoice(invoice);

        return response;
	}

}
