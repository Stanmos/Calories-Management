package com.nutrition.calc.service.jdbc;

import com.nutrition.calc.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static com.nutrition.calc.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}
