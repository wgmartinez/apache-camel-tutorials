
package co.wgmartinez.camel.orders.model;

import java.io.Serializable;
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
    "paymentType",
    "paymentId",
    "expiryDate"
})
public class PaymentDetails implements Serializable {

    @JsonProperty("paymentType")
    private String paymentType;
    @JsonProperty("paymentId")
    private String paymentId;
    @JsonProperty("expiryDate")
    private String expiryDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("paymentType")
    public String getPaymentType() {
        return paymentType;
    }

    @JsonProperty("paymentType")
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentDetails withPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    @JsonProperty("paymentId")
    public String getPaymentId() {
        return paymentId;
    }

    @JsonProperty("paymentId")
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentDetails withPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    @JsonProperty("expiryDate")
    public String getExpiryDate() {
        return expiryDate;
    }

    @JsonProperty("expiryDate")
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public PaymentDetails withExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
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

    public PaymentDetails withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("paymentType", paymentType).append("paymentId", paymentId).append("expiryDate", expiryDate).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(expiryDate).append(additionalProperties).append(paymentId).append(paymentType).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PaymentDetails) == false) {
            return false;
        }
        PaymentDetails rhs = ((PaymentDetails) other);
        return new EqualsBuilder().append(expiryDate, rhs.expiryDate).append(additionalProperties, rhs.additionalProperties).append(paymentId, rhs.paymentId).append(paymentType, rhs.paymentType).isEquals();
    }

}
