
package co.wgmartinez.camel.orders.model;

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
    "description",
    "dimension",
    "materials"
})
public class Specification {

    @JsonProperty("description")
    private String description;
    @JsonProperty("dimension")
    private String dimension;
    @JsonProperty("materials")
    private String materials;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Specification withDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonProperty("dimension")
    public String getDimension() {
        return dimension;
    }

    @JsonProperty("dimension")
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Specification withDimension(String dimension) {
        this.dimension = dimension;
        return this;
    }

    @JsonProperty("materials")
    public String getMaterials() {
        return materials;
    }

    @JsonProperty("materials")
    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public Specification withMaterials(String materials) {
        this.materials = materials;
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

    public Specification withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("description", description).append("dimension", dimension).append("materials", materials).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(description).append(additionalProperties).append(dimension).append(materials).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Specification) == false) {
            return false;
        }
        Specification rhs = ((Specification) other);
        return new EqualsBuilder().append(description, rhs.description).append(additionalProperties, rhs.additionalProperties).append(dimension, rhs.dimension).append(materials, rhs.materials).isEquals();
    }

}
