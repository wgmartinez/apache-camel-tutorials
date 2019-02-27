package co.wgmartinez.camel.orders.controller;

import co.wgmartinez.camel.orders.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OrderValidationController {

    @Autowired
    private OrderValidationService orderValidationService;

    @RequestMapping(value = "/order/sync/illustrateDsl", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Order> illustrateDsl(@Valid @RequestBody Order request) throws Exception {
        Order orderResponse = orderValidationService.illustrateDsl(request);
        ResponseEntity<Order> responseEntity = new ResponseEntity<>(orderResponse, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/order/sync/splitImmediateAggregate", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Order> splitImmediateAggregate(@Valid @RequestBody Order request) throws Exception {
        Order orderResponse = orderValidationService.splitAggregate(request);
        ResponseEntity<Order> responseEntity = new ResponseEntity<>(orderResponse, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/order/sync/multicastImmediateAggregate", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Order> multicastImmediateAggregate(@Valid @RequestBody Order request) throws Exception {
        Order orderResponse = orderValidationService.multicastImmediateAggregate(request);
        ResponseEntity<Order> responseEntity = new ResponseEntity<>(orderResponse, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/order/sync/multicastDelayedAggregate", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Order> multicastDelayedAggregate(@Valid @RequestBody Order request) throws Exception {
        Order orderResponse = orderValidationService.multicastDelayedAggregate(request);
        ResponseEntity<Order> responseEntity = new ResponseEntity<>(orderResponse, HttpStatus.OK);
        return responseEntity;
    }
}
