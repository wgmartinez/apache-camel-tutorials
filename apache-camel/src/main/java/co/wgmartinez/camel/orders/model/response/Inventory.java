
package co.wgmartinez.camel.orders.model.response;

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
    "availableItems",
    "location"
})
public class Inventory {

    @JsonProperty("availableItems")
    private String availableItems;
    @JsonProperty("location")
    private String location;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("availableItems")
    public String getAvailableItems() {
        return availableItems;
    }

    @JsonProperty("availableItems")
    public void setAvailableItems(String availableItems) {
        this.availableItems = availableItems;
    }

    public Inventory withAvailableItems(String availableItems) {
        this.availableItems = availableItems;
        return this;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    public Inventory withLocation(String location) {
        this.location = location;
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

    public Inventory withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("availableItems", availableItems).append("location", location).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(availableItems).append(location).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Inventory) == false) {
            return false;
        }
        Inventory rhs = ((Inventory) other);
        return new EqualsBuilder().append(availableItems, rhs.availableItems).append(location, rhs.location).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
