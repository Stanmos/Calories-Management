package com.nutrition.calc.web.user;

import com.nutrition.calc.repository.inmemory.InMemoryUserRepository;
import com.nutrition.calc.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static com.nutrition.calc.UserTestData.NOT_FOUND;
import static com.nutrition.calc.UserTestData.USER_ID;

@SpringJUnitConfig(locations = {"classpath:spring/spring-app.xml", "classpath:spring/inmemory.xml"})
public class InMemoryAdminRestControllerSpringTest {
    @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepository repository;

    @BeforeEach
    public void setUp() {
        repository.init();
    }

    @Test
    public void delete() {
        controller.delete(USER_ID);
        Assertions.assertNull(repository.get(USER_ID));
    }

    @Test
    public void deleteNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }
}
