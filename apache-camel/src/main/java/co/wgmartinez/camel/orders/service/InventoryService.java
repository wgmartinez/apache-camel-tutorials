package co.wgmartinez.camel.orders.service;


import co.wgmartinez.camel.orders.model.Item;
import co.wgmartinez.camel.orders.model.Specification;

public interface InventoryService {
    Item checkInventory(Item item) throws InventoryException;

    Specification getSpecification(Item item);
}
