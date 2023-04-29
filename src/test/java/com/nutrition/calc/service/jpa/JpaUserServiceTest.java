package com.nutrition.calc.service.jpa;

import com.nutrition.calc.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.nutrition.calc.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaUserServiceTest extends AbstractUserServiceTest {
}
