package com.nutrition.calc.service.jpa;

import com.nutrition.calc.service.AbstractMealServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.nutrition.calc.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaMealServiceTest extends AbstractMealServiceTest {
}
