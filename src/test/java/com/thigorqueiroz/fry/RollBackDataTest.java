package com.thigorqueiroz.fry;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Rollback
@Transactional
public interface RollBackDataTest {
}
