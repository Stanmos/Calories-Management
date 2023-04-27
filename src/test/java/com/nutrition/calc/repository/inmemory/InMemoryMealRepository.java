package com.nutrition.calc.repository.inmemory;

import com.nutrition.calc.MealTestData;
import com.nutrition.calc.UserTestData;
import com.nutrition.calc.model.Meal;
import com.nutrition.calc.repository.MealRepository;
import com.nutrition.calc.util.Util;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

@Repository
public class InMemoryMealRepository implements MealRepository {
    // Map  userId -> mealRepository
    private final Map<Integer, InMemoryBaseRepository<Meal>> usersMealsMap = new ConcurrentHashMap<>();

    {
        var userMeals = new InMemoryBaseRepository<Meal>();
        MealTestData.meals.forEach(userMeals::put);
        usersMealsMap.put(UserTestData.USER_ID, userMeals);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Objects.requireNonNull(meal, "meal must not be null");
        var meals = usersMealsMap.computeIfAbsent(userId, uId -> new InMemoryBaseRepository<>());
        return meals.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        var meals = usersMealsMap.get(userId);
        return meals != null && meals.delete(id);
    }

    @Override
    public Meal get(int id, int userId) {
        var meals = usersMealsMap.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return filterByPredicate(userId, meal -> Util.isBetweenHalfOpen(meal.getDateTime(), startDateTime, endDateTime));
    }

    @Override
    public List<Meal> getAll(int userId) {
        return filterByPredicate(userId, meal -> true);
    }

    private List<Meal> filterByPredicate(int userId, Predicate<Meal> filter) {
        var meals = usersMealsMap.get(userId);
        return meals == null ? Collections.emptyList() :
                meals.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                        .toList();
    }
}
