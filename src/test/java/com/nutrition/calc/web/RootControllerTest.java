package com.nutrition.calc.web;

import com.nutrition.calc.UserTestData;
import org.junit.jupiter.api.Test;

import static com.nutrition.calc.MealTestData.meals;
import static com.nutrition.calc.TestUtil.userAuth;
import static com.nutrition.calc.UserTestData.admin;
import static com.nutrition.calc.UserTestData.user;
import static com.nutrition.calc.model.AbstractBaseEntity.START_SEQ;
import static com.nutrition.calc.util.MealsUtil.getTos;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void getUsers() throws Exception {
        perform(get("/users")
                .with(userAuth(admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"));
    }

    @Test
    void unAuth() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void getMeals() throws Exception {
        perform(get("/meals")
                .with(userAuth(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meal"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meal.jsp"));
    }
}
