package by.overone.restaurant.controller;

import by.overone.restaurant.entity.ClassBeforeCreateUser;
import by.overone.restaurant.entity.Detail;
import by.overone.restaurant.entity.Order;
import by.overone.restaurant.entity.User;
import by.overone.restaurant.entity.enums.Role;
import by.overone.restaurant.service.impl.DetailService;
import by.overone.restaurant.service.impl.OrderService;
import by.overone.restaurant.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DetailService detailService;

    @GetMapping("orders")
    public String getMenuPage(Model model, HttpSession session) {
        List<User> users = userService.findAll();
        String username = (String) session.getAttribute("username");


        List<Order> orderList = userService.findOrdersByUsername(username);
        model.addAttribute("orders", orderList);
        return "orders";
    }

    @GetMapping("paidOrder")
    public String paidOrder(@RequestParam(name = "orderId") Long orderId, HttpSession session) {
        orderService.paidOrder(orderId, (String) session.getAttribute("username"));
        return "orders";
    }

    @GetMapping("registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new ClassBeforeCreateUser());
        return "registration";
    }

    @PostMapping("registration")
    public String registrationUserSubmit(@ModelAttribute("user") ClassBeforeCreateUser preUser) {
        Detail newDetail = new Detail(preUser.getName(),
                preUser.getSurname(),
                preUser.getPhoneNumber(),
                preUser.getEmail());
        newDetail = detailService.save(newDetail);
        System.out.println(newDetail);

        User user = new User(preUser.getUsername(),
                passwordEncoder.encode(preUser.getPassword()),
                Role.USER, 100.0, 1, newDetail);
        userService.create(user);
        System.out.println(user);
        return "user/confirm";
    }
}
