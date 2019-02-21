package co.wgmartinez.camel.orders.converter;

import co.wgmartinez.camel.orders.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.*;

import java.io.IOException;

@Converter

public class StringToItem implements TypeConverter {

    @Override
    public boolean allowNull() {
        return false;
    }

    @Override
    public <T> T convertTo(Class<T> type, Object value) throws TypeConversionException {

        ObjectMapper mapper = new ObjectMapper();
        String val = (String) value;
        Item item = new Item();

        try {
            item = mapper.readValue(val, Item.class);
        }catch (IOException e){
            throw new TypeConversionException(value, type, e);
        }

        return (T) item;
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
