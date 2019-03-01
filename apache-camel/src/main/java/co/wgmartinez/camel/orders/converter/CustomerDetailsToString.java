package co.wgmartinez.camel.orders.converter1;

import co.wgmartinez.camel.orders.model.CustomerDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.*;

import java.io.IOException;

@Converter
public class CustomerDetailsToString implements TypeConverter {

    @Override
    public boolean allowNull() {
        return true;
    }

    @Override
    public <T> T convertTo(Class<T> type, Object value) throws TypeConversionException {


        ObjectMapper mapper = new ObjectMapper();
        CustomerDetails val = (CustomerDetails) value;
        String customerDetails = null;

        try {
            customerDetails = mapper.writeValueAsString(val);
        }catch (IOException e){
            throw new TypeConversionException(value, type, e);
        }

        return (T) customerDetails;
    }

    @Override
    public <T> T convertTo(Class<T> type, Exchange exchange, Object value) throws TypeConversionException {
        return (T) convertTo(String.class, value);
    }

    @Override
    public <T> T mandatoryConvertTo(Class<T> type, Object value) throws TypeConversionException, NoTypeConversionAvailableException {
        return (T) convertTo(String.class, value);
    }

    @Override
    public <T> T mandatoryConvertTo(Class<T> type, Exchange exchange, Object value) throws TypeConversionException, NoTypeConversionAvailableException {
        return (T) convertTo(String.class, value);
    }

    @Override
    public <T> T tryConvertTo(Class<T> type, Object value) {
        return (T) convertTo(String.class, value);
    }

    @Override
    public <T> T tryConvertTo(Class<T> type, Exchange exchange, Object value) {
        return (T) convertTo(String.class, value);
    }
}
