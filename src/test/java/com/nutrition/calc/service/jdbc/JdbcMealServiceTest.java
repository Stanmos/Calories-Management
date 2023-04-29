package com.nutrition.calc.service.jdbc;

import com.nutrition.calc.service.AbstractMealServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.nutrition.calc.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcMealServiceTest extends AbstractMealServiceTest {
}
