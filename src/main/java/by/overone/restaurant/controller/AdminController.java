package by.overone.restaurant.controller;

import by.overone.restaurant.entity.*;
import by.overone.restaurant.entity.dto.UserDTO;
import by.overone.restaurant.entity.enums.OrderStatus;
import by.overone.restaurant.entity.enums.Role;
import by.overone.restaurant.service.impl.DishService;
import by.overone.restaurant.service.impl.OrderService;
import by.overone.restaurant.service.impl.UserService;
import by.overone.restaurant.service.impl.UserMappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMappingUtils userMappingUtils;

    @GetMapping("admin_panel")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String workPanel() {
        return "admin_panel/admin_panel";
    }

    @GetMapping("view_orders")
    @PreAuthorize("hasAnyAuthority('admin:read', 'admin:write')")
    public String viewAllOrders(Model model) {
        List<Order> orderList = orderService.findAll();
        model.addAttribute("orders", orderList);
        return "admin_panel/view_orders";
    }

    @GetMapping("order_to_confirm")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String confirmOrderToConfirm(Model model) {
        List<Order> orderList = orderService.findAll();
        model.addAttribute("orders", orderList.stream()
                .filter(d -> d.getStatus().equals(OrderStatus.VERIFICATION))
                .collect(Collectors.toList()));
        return "admin_panel/order_to_confirm";
    }

    @GetMapping("confirm_order")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String confirmOrder(@RequestParam(name = "orderId") Long orderId) {
        Order order = orderService.findById(orderId);
        order.setStatus(OrderStatus.CONFIRMED);
        orderService.create(order);
        return "admin_panel/order_to_confirm";
    }

//    @PostMapping("add_dish")
//    public String addDish(@ModelAttribute("dishes")  Dish newDish) {
//        Dish newDish = new Dish(newDish.getName(),
//                newDish.getDescription(),
//                newDish.getCategory());
//        System.out.println(newDish);
//        DishService.create(newDish);
//        return "admin_panel/admin_panel";
//    }

        @GetMapping("view_users")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String replenishmentBalance(Model model) {
        List<User> userList = userService.findAll();
        List<UserDTO> userDTOList = userList.stream()
                .map(user -> userMappingUtils.mapToDto(user))
                .collect(Collectors.toList());
        model.addAttribute("usersDTO", userDTOList);
        return "admin_panel/view_users";
    }
}
