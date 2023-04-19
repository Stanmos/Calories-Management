package com.nutrition.calc.web;

import com.nutrition.calc.model.AbstractBaseEntity;

import static com.nutrition.calc.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ;
    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}
