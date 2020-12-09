package com.thigorqueiroz.fry;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class AbstractBaseTest {
    public AbstractBaseTest() {
        FixtureFactoryLoader.loadTemplates("com.thigorqueiroz.fry");
    }
}
