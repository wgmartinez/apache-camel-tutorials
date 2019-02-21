package co.wgmartinez.camel.orders.service;

import co.wgmartinez.camel.orders.model.Item;
import co.wgmartinez.camel.orders.model.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecificationService {

    @Autowired
    InventoryService inventoryService;

    public Specification getSpecification(Item item) throws Exception{
//        Thread.sleep(1000);
        return inventoryService.getSpecification(item);
    }
}
