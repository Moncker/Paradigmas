package core.e2e;

import core.OWM.App;
import core.SimpleWeather;
import org.junit.After;
import org.junit.Before;

public abstract class E2ETestBed {
        protected SimpleWeather simpleWeather;

        @Before
        public void setUp() throws Exception {
                boolean isMock = true;
                if (isMock)
                        simpleWeather = new SUT();
                else
                        simpleWeather = new App();
        }

        @After
        public void tearDown() throws Exception {
                simpleWeather = null;
        }

}