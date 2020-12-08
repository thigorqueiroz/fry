package com.thigorqueiroz.fry.repository;

import com.thigorqueiroz.fry.AbstractBaseTest;
import com.thigorqueiroz.fry.port.adapter.config.JdbcConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

@JdbcTest
@ImportAutoConfiguration(classes = {JdbcConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AbstractRepositoryTest extends AbstractBaseTest {

}
