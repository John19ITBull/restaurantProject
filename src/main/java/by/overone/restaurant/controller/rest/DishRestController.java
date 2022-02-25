package by.overone.restaurant.controller.rest;

import by.overone.restaurant.entity.Dish;
import by.overone.restaurant.exception.NoSuchRestaurantException;
import by.overone.restaurant.service.impl.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishRestController {

    @Autowired
    private DishService dishService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_USER', 'ROLE_ADMIN')")
    public List<Dish> findAll() {
        return dishService.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public Dish findById(@PathVariable("id") Long id) {
        return dishService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public Dish create(@RequestBody Dish dish) {
        dishService.create(dish);
        return dish;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public Dish update(@RequestBody Dish dish) {
        dishService.create(dish);
        return dish;
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public String delete(@PathVariable Long id) {
        if (dishService.findById(id) == null) {
            throw new NoSuchRestaurantException("There is no employee with ID = " + id + " in database.");
        }
        dishService.delete(id);
        return "Employee with ID = " + id + " was deleted";
    }
}
