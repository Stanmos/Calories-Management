package com.nutrition.calc.web;

import com.nutrition.calc.UserTestData;
import org.junit.jupiter.api.Test;

import static com.nutrition.calc.MealTestData.meals;
import static com.nutrition.calc.model.AbstractBaseEntity.START_SEQ;
import static com.nutrition.calc.util.MealsUtil.getTos;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void getUsers() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"));
    }

    @Test
    public void getMeals() throws Exception {
        perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meal"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meal.jsp"))
                .andExpect(model().attribute("meals", getTos(meals, SecurityUtil.authUserCaloriesPerDay())));
    }
}
