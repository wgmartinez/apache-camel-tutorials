package co.wgmartinez.microservices.inventory.controller;

import co.wgmartinez.camel.orders.model.Item;
import co.wgmartinez.camel.orders.model.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {


    @RequestMapping(value = "/item/{code}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Item> receiveOrderRequest(@PathVariable("code") String code) throws Exception {

        Item item = new Item();
        if (code.contains("SAL")){
            item.setDescription("Speed Cross 5");
            Specification specification = new Specification();
            item.setSpecification(specification);

            specification.setDescription("Designed for running. THE REBIRTH OF SALOMON'S LEGENDARY TRAIL SHOE WITH UNMATCHED GRIP AND FIT.");
            specification.setMaterials("SPEEDCROSS 5 uses a completely welded upper, with dissociated sensifit arms. it moves more naturally with your foot, and dials in the comfort.");
            specification.setDimension("Size 7'', 8'', 9'' ");

            item.setDescription("Designed for running. THE REBIRTH OF SALOMON'S LEGENDARY TRAIL SHOE WITH UNMATCHED GRIP AND FIT.");
            item.setCode("SC5");
        }


        ResponseEntity<Item> responseEntity = new ResponseEntity<>(item, HttpStatus.OK);

        return responseEntity;
    }
}
