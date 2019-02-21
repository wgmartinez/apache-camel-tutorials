
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
    "code",
    "description",
    "quantity",
    "price",
    "specification",
    "inventory"
})
public class Item {

    @JsonProperty("code")
    private String code;
    @JsonProperty("description")
    private String description;
    @JsonProperty("quantity")
    private String quantity;
    @JsonProperty("price")
    private String price;
    @JsonProperty("specification")
    private Specification specification;
    @JsonProperty("inventory")
    private Inventory inventory;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    public Item withCode(String code) {
        this.code = code;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Item withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("quantity")
    public String getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Item withQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    public Item withPrice(String price) {
        this.price = price;
        return this;
    }

    @JsonProperty("specification")
    public Specification getSpecification() {
        return specification;
    }

    @JsonProperty("specification")
    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public Item withSpecification(Specification specification) {
        this.specification = specification;
        return this;
    }

    @JsonProperty("inventory")
    public Inventory getInventory() {
        return inventory;
    }

    @JsonProperty("inventory")
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Item withInventory(Inventory inventory) {
        this.inventory = inventory;
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

    public Item withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("code", code).append("description", description).append("quantity", quantity).append("price", price).append("specification", specification).append("inventory", inventory).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(code).append(quantity).append(price).append(description).append(specification).append(additionalProperties).append(inventory).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Item) == false) {
            return false;
        }
        Item rhs = ((Item) other);
        return new EqualsBuilder().append(code, rhs.code).append(quantity, rhs.quantity).append(price, rhs.price).append(description, rhs.description).append(specification, rhs.specification).append(additionalProperties, rhs.additionalProperties).append(inventory, rhs.inventory).isEquals();
    }

}
