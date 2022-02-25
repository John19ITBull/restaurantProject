package by.overone.restaurant.controller;

import by.overone.restaurant.entity.ApplicationUser;
import by.overone.restaurant.entity.Dish;
import by.overone.restaurant.service.impl.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private DishService dishService;

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("index")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("main")
    public String getMainPage(HttpSession session) {
        ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", user.getUsername());
        session.setAttribute("basket_quantity", "0");
        return "main";
    }

    @GetMapping("menu_unreg")
    public String getMenuForUnregUsersPage(Model model) {
        List<Dish> dishList = dishService.findAll();
        model.addAttribute("dishes", dishList);
        return "menu_unreg";
    }
}
