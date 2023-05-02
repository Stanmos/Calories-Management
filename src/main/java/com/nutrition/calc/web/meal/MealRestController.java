package com.nutrition.calc.web.meal;

import com.nutrition.calc.model.Meal;
import com.nutrition.calc.service.MealService;
import com.nutrition.calc.to.MealTo;
import com.nutrition.calc.util.MealsUtil;
import com.nutrition.calc.web.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.nutrition.calc.util.ValidationUtil.assureIdConsistent;
import static com.nutrition.calc.util.ValidationUtil.checkNew;

@Controller
public class MealRestController extends AbstractMealController{

}
