package com.nutrition.calc.web.user;

import com.nutrition.calc.repository.inmemory.InMemoryUserRepository;
import com.nutrition.calc.util.exception.NotFoundException;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static com.nutrition.calc.UserTestData.NOT_FOUND;
import static com.nutrition.calc.UserTestData.USER_ID;

public class InMemoryAdminRestControllerTest {
    private static final Logger log = LoggerFactory.getLogger(InMemoryAdminRestControllerTest.class);

    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;
    private static InMemoryUserRepository repository;

    @BeforeAll
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/inmemory.xml");
        log.info("\n{}\n", Arrays.toString(appCtx.getBeanDefinitionNames()));
        controller = appCtx.getBean(AdminRestController.class);
        repository = appCtx.getBean(InMemoryUserRepository.class);
    }

    @AfterAll
    public static void afterClass() {
        appCtx.close();
    }

    @BeforeEach
    public void setup() {
        // re-initialize
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
