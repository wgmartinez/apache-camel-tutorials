package co.wgmartinez.camel.orders.service;

import co.wgmartinez.camel.orders.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WarehouseService {

    @Autowired
    InventoryService inventoryService;

    public Item checkInventory(Item item) throws Exception{
        return inventoryService.checkInventory(item);
    }
}
