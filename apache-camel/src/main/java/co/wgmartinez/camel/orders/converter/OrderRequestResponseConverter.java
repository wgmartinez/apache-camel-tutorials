package co.wgmartinez.camel.orders.converter;

import co.wgmartinez.camel.orders.model.request.OrderRequest;
import co.wgmartinez.camel.orders.model.response.*;
import org.apache.camel.*;

import java.util.List;
import java.util.stream.Collectors;

@Converter
public class OrderRequestResponseConverter implements TypeConverter {

    @Override
    public boolean allowNull() {
        return false;
    }

    @Override
    public <T> T convertTo(Class<T> type, Object value) throws TypeConversionException {
        OrderResponse orderResponse = new OrderResponse();
        OrderRequest request = (OrderRequest) value;

        orderResponse.setCustomerId(request.getCustomerId());

        DeliveryDetails deliveryDetails = new DeliveryDetails();
        deliveryDetails.setAddress(request.getCustomerDetails().getDeliveryDetails().getAddress());
        deliveryDetails.setCity(request.getCustomerDetails().getDeliveryDetails().getCity());
        deliveryDetails.setPostcode(request.getCustomerDetails().getDeliveryDetails().getPostcode());
        deliveryDetails.setState(request.getCustomerDetails().getDeliveryDetails().getState());

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setDeliveryDetails(deliveryDetails);
        orderResponse.setCustomerDetails(customerDetails);

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setExpiryDate(request.getCustomerDetails().getPaymentDetails().getExpiryDate());
        paymentDetails.setPaymentId(request.getCustomerDetails().getPaymentDetails().getPaymentId());
        paymentDetails.setPaymentType(request.getCustomerDetails().getPaymentDetails().getPaymentType());
        customerDetails.setPaymentDetails(paymentDetails);


        OrderDetails orderDetails = new OrderDetails();
        List<Item> items = request.getOrderDetails().getItems().stream()
                .map(i -> {
                    Item item = new Item();
                    item.setQuantity(i.getQuantity());
                    item.setDescription(i.getDescription());
                    item.setCode(i.getCode());
                    item.setQuantity(i.getQuantity());
                    item.setPrice(i.getPrice());

                    Inventory inventory = new Inventory();
                    inventory.setLocation(i.getInventory().getLocation());
                    inventory.setAvailableItems(i.getInventory().getAvailableItems());
                    item.setInventory(inventory);

                    Specification specification = new Specification();
                    specification.setMaterials(i.getSpecification().getMaterials());
                    specification.setDimension(i.getSpecification().getDimension());
                    specification.setDescription(i.getSpecification().getDescription());
                    item.setSpecification(specification);
                    return item;
                }).collect(Collectors.toList());

        orderDetails.setItems(items);
        orderResponse.setOrderDetails(orderDetails);

        return (T) orderResponse;
    }

    @Override
    public <T> T convertTo(Class<T> type, Exchange exchange, Object value) throws TypeConversionException {
        return convertTo(type, value);
    }

    @Override
    public <T> T mandatoryConvertTo(Class<T> type, Object value) throws TypeConversionException, NoTypeConversionAvailableException {
        return convertTo(type, value);
    }

    @Override
    public <T> T mandatoryConvertTo(Class<T> type, Exchange exchange, Object value) throws TypeConversionException, NoTypeConversionAvailableException {
        return convertTo(type, value);
    }

    @Override
    public <T> T tryConvertTo(Class<T> type, Object value) {
        return convertTo(type, value);
    }

    @Override
    public <T> T tryConvertTo(Class<T> type, Exchange exchange, Object value) {
        return convertTo(type, value);
    }
}
