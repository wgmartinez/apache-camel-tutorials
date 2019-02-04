package co.wgmartinez.microservices.inventory.controller;

import co.wgmartinez.camel.orders.model.CustomerDetails;
import co.wgmartinez.camel.orders.model.DeliveryDetails;
import co.wgmartinez.camel.orders.model.PaymentDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerDetailsFactory {
    Map<String, CustomerDetails> map = new HashMap<>();

    public CustomerDetailsFactory(){
        buildCustomerDetails();
    }


    private void buildCustomerDetails() {
        CustomerDetails myDetails = new CustomerDetails();

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentId("1234-5678-9689");
        paymentDetails.setPaymentType("VISA");
        paymentDetails.setExpiryDate("12/23");
        myDetails.setPaymentDetails(paymentDetails);

        DeliveryDetails deliveryDetails = new DeliveryDetails();
        deliveryDetails.setAddress("4 Brave Court");
        deliveryDetails.setCity("Carnes Hill");
        deliveryDetails.setPostcode("2171");
        deliveryDetails.setState("NSW");

        myDetails.setDeliveryDetails(deliveryDetails);
        myDetails.setAdditionalProperty("Test","Test");

        map.put("WGM123", myDetails);

        CustomerDetails herDetails = new CustomerDetails();

        paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentId("4444-5657-7777-0000");
        paymentDetails.setPaymentType("MASTERCARD");
        paymentDetails.setExpiryDate("01/24");
        herDetails.setPaymentDetails(paymentDetails);

        deliveryDetails = new DeliveryDetails();
        deliveryDetails.setAddress("4 Brave Court");
        deliveryDetails.setCity("Carnes Hill");
        deliveryDetails.setPostcode("2171");
        deliveryDetails.setState("NSW");

        herDetails.setDeliveryDetails(deliveryDetails);
        herDetails.setAdditionalProperty("Test","Test");

        map.put("EVA123", herDetails);
    }

    public CustomerDetails getCustomerDetaulsForId(String id){
        return map.get(id);
    }
}
