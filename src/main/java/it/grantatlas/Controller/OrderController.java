package it.grantatlas.Controller;

import it.grantatlas.Entity.Order;
import it.grantatlas.Payload.ApiResponse;
import it.grantatlas.Payload.OrderDto;
import it.grantatlas.Repository.OrderRepository;
import it.grantatlas.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/order")
public class OrderController {

    private final OrderService orderService;

    private final OrderRepository orderRepository;

    @GetMapping
    public HttpEntity<?> getAll() {
        List<Order> all = orderRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public HttpEntity<?> ordering(@RequestBody OrderDto orderDto) {
        ApiResponse ordering = orderService.ordering(orderDto);
        return ResponseEntity.status(ordering.success() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(ordering);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOrder(@PathVariable UUID id){
        ApiResponse apiResponse = orderService.deleteOrder(id);
        return ResponseEntity.status(apiResponse.success() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
