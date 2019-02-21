
package co.wgmartinez.camel.orders.model.request;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "customerId",
    "customerDetails",
    "orderDetails"
})
public class OrderRequest {

    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("customerDetails")
    private CustomerDetails customerDetails;
    @JsonProperty("orderDetails")
    private OrderDetails orderDetails;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("customerId")
    public String getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public OrderRequest withCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    @JsonProperty("customerDetails")
    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    @JsonProperty("customerDetails")
    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public OrderRequest withCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
        return this;
    }

    @JsonProperty("orderDetails")
    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    @JsonProperty("orderDetails")
    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderRequest withOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public OrderRequest withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("customerId", customerId).append("customerDetails", customerDetails).append("orderDetails", orderDetails).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(customerId).append(orderDetails).append(additionalProperties).append(customerDetails).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrderRequest) == false) {
            return false;
        }
        OrderRequest rhs = ((OrderRequest) other);
        return new EqualsBuilder().append(customerId, rhs.customerId).append(orderDetails, rhs.orderDetails).append(additionalProperties, rhs.additionalProperties).append(customerDetails, rhs.customerDetails).isEquals();
    }

}
