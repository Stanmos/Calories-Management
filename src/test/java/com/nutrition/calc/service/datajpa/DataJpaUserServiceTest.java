package com.nutrition.calc.service.datajpa;

import com.nutrition.calc.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.nutrition.calc.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
}
