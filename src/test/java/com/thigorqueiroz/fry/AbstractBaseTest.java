package com.thigorqueiroz.fry;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class AbstractBaseTest {

    /*@BeforeClass
    public void setup() {
        FixtureFactoryLoader.loadTemplates("com.thigorqueiroz.fry");
    }*/

    public AbstractBaseTest() {
        FixtureFactoryLoader.loadTemplates("com.thigorqueiroz.fry");
    }


}
