package co.wgmartinez.camel.orders.service;

import co.wgmartinez.camel.orders.model.Item;
import co.wgmartinez.camel.orders.model.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryFactory factory;

    @Override
    public Item checkInventory(Item item) throws InventoryException {

        if (item.getCode().contains("SAL")){
            return factory.createSALX123(item);
        }

        return factory.createOther(item);
    }


    @Override
    public Specification getSpecification(Item item) {
        if (item.getCode().contains("SAL")){
            return factory.createSALSpecification();
        }

        return factory.createOtherSpecification();
    }
}
