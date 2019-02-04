package co.wgmartinez.microservices.inventory.controller;

import co.wgmartinez.camel.orders.model.CustomerDetails;
import co.wgmartinez.camel.orders.model.DeliveryDetails;
import co.wgmartinez.camel.orders.model.PaymentDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerDetailsController {

    /*
    Dummy REST API that returns a customers payment and delivery details
    */
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CustomerDetails> receiveOrderRequest(@PathVariable("id") String id) throws Exception {
        CustomerDetails customerDetails = new CustomerDetails();

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentId("1234-5678-9689");
        paymentDetails.setPaymentType("VISA");
        paymentDetails.setExpiryDate("12/23");
        customerDetails.setPaymentDetails(paymentDetails);

        DeliveryDetails deliveryDetails = new DeliveryDetails();
        deliveryDetails.setAddress("4 Brave Court");
        deliveryDetails.setCity("Carnes Hill");
        deliveryDetails.setPostcode("2171");
        deliveryDetails.setState("NSW");

        customerDetails.setDeliveryDetails(deliveryDetails);
        customerDetails.setAdditionalProperty("Test", "Test");

        ResponseEntity<CustomerDetails> responseEntity = new ResponseEntity<>(customerDetails, HttpStatus.OK);

        return responseEntity;
    }
}
