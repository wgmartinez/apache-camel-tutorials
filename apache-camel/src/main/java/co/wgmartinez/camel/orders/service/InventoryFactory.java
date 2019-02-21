package co.wgmartinez.camel.orders.service;

import co.wgmartinez.camel.orders.model.Inventory;
import co.wgmartinez.camel.orders.model.Item;
import co.wgmartinez.camel.orders.model.Specification;
import org.springframework.stereotype.Component;

@Component
public class InventoryFactory {

    public Item createSALX123(Item i){
        Item item = new Item();
        item.setCode(i.getCode());
        item.setDescription(i.getDescription());
        item.setPrice("$120.00");
        item.setQuantity(i.getQuantity());

        Inventory inventory = new Inventory();
        inventory.setAvailableItems("100");
        inventory.setLocation("Manly");

        item.setInventory(inventory);
        return item;
    }


    public Specification createSALSpecification(){
        Specification specification = new Specification();
        specification.setDescription("Brilliant for hill and trails running, great grip on muddy surfaces");
        specification.setDimension("Super light");
        specification.setMaterials("Made of state of the art light and durable materials");
        return specification;
    }


    public Item createOther(Item i){
        Item item = new Item();
        item.setCode(i.getCode());
        item.setDescription(i.getDescription());
        item.setPrice("$100.00");
        item.setQuantity(i.getQuantity());

        Inventory inventory = new Inventory();
        inventory.setAvailableItems("2");
        inventory.setLocation("Melbourne");

        item.setInventory(inventory);

        return item;
    }


    public Specification createOtherSpecification(){
        Specification specification = new Specification();
        specification.setDescription("Good for snow and wet surfaces");
        specification.setDimension("Medium light");
        specification.setMaterials("Made of state of the art light and durable materials");
        return specification;
    }


}
