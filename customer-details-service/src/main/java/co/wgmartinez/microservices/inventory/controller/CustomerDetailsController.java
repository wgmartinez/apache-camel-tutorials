package co.wgmartinez.microservices.inventory.controller;

import co.wgmartinez.camel.orders.model.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerDetailsController {

    @Autowired
    private CustomerDetailsFactory factory;

    /*
    Dummy REST API that returns a customers payment and delivery details
    */
    @RequestMapping(value = "/customer", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CustomerDetails> receiveOrderRequest(@RequestParam("id") String id) throws Exception {

        System.out.println("Received request param = " + id);
        CustomerDetails customerDetails = factory.getCustomerDetaulsForId(id);
        System.out.println("CustomerDetails = " + customerDetails);

        ResponseEntity<CustomerDetails> responseEntity = new ResponseEntity<>(customerDetails, HttpStatus.OK);
        return responseEntity;
    }
}
