package co.wgmartinez.camel.orders.controller;

import co.wgmartinez.camel.orders.invoicing.OrderInvoiceProcess;
import co.wgmartinez.camel.orders.model.Order;
import cxf.wsdl.wgmartinez.co.invoice_ws.AddInvoiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OrderProcessingController {

    @Autowired
    private OrderInvoiceProcess orderProcess;

    @RequestMapping(value = "/order/process", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Order> processOrder(@Valid @RequestBody Order request) throws Exception {
        Order response  = orderProcess.process(request);
        ResponseEntity<Order> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        return responseEntity;
    }

}
