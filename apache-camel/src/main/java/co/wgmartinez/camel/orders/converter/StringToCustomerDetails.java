package co.wgmartinez.camel.orders.converter;

import co.wgmartinez.camel.orders.model.CustomerDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.*;

import java.io.IOException;

@Converter

public class StringToCustomerDetails implements TypeConverter {

    @Override
    public boolean allowNull() {
        return false;
    }

    @Override
    public <T> T convertTo(Class<T> type, Object value) throws TypeConversionException {

        ObjectMapper mapper = new ObjectMapper();
        String val = (String) value;
        CustomerDetails customerDetails = null;

        try {
            customerDetails = mapper.readValue(val, CustomerDetails.class);
        }catch (IOException e){
            throw new TypeConversionException(value, type, e);
        }

        return (T) customerDetails;
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
