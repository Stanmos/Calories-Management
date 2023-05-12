package com.nutrition.calc.web;

import com.nutrition.calc.service.MealService;
import com.nutrition.calc.service.UserService;
import com.nutrition.calc.util.MealsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private MealService mealService;

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "index";
    }

    @GetMapping("/users")
    public String getUsers() {
        log.info("users");
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        log.info("setUser {}", userId);
        SecurityUtil.setAuthUserId(userId);
        return "redirect:meals";
    }

    @GetMapping("/meals")
    public String getMeals(Model model) {
        log.info("meals");
        model.addAttribute("meals",
                MealsUtil.getTos(mealService.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay()));
        return "meal";
    }
}
