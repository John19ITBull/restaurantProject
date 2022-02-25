package by.overone.restaurant.controller.rest;

import by.overone.restaurant.entity.User;
import by.overone.restaurant.exception.NoSuchRestaurantException;
import by.overone.restaurant.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public User create(@RequestBody User user) {
        userService.create(user);
        return user;
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public User update(@RequestBody User user) {
        userService.create(user);
        return user;
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public String delete(@PathVariable Long id) {
        if (userService.findById(id) == null) {
            throw new NoSuchRestaurantException("There is no employee with ID = " + id + " in database.");
        }
        userService.delete(id);
        return "Employee with ID = " + id + " was deleted";
    }
}
