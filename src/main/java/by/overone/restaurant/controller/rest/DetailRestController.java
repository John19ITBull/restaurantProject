package by.overone.restaurant.controller.rest;

import by.overone.restaurant.entity.Detail;
import by.overone.restaurant.exception.NoSuchRestaurantException;
import by.overone.restaurant.service.impl.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/details")
public class DetailRestController {
    @Autowired
    private DetailService detailService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public List<Detail> findAll() {
        return detailService.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public Detail findById(@PathVariable("id") Long id) {
        return detailService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public Detail create(@RequestBody Detail detail) {
        detailService.create(detail);
        return detail;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public Detail update(@RequestBody Detail detail) {
        detailService.create(detail);
        return detail;
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public String delete(@PathVariable Long id) {
        if (detailService.findById(id) == null) {
            throw new NoSuchRestaurantException("There is no employee with ID = " + id + " in database.");
        }
        detailService.delete(id);
        return "Employee with ID = " + id + " was deleted";
    }
}
