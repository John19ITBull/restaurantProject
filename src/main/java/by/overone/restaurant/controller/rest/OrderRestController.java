package by.overone.restaurant.controller.rest;

import by.overone.restaurant.entity.Order;
import by.overone.restaurant.exception.NoSuchRestaurantException;
import by.overone.restaurant.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public Order findById(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public Order create(@RequestBody Order order) {
        orderService.create(order);
        return order;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public Order update(@RequestBody Order order) {
        orderService.create(order);
        return order;
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public String delete(@PathVariable Long id) {
        if (orderService.findById(id) == null) {
            throw new NoSuchRestaurantException("There is no employee with ID = " + id + " in database.");
        }
        orderService.delete(id);
        return "Employee with ID = " + id + " was deleted";
    }
}
