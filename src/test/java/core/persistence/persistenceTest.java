package core.persistence;

import org.junit.After;
import org.junit.Before;


public abstract class persistenceTest {

    protected Connector connector;

    @Before
    public void setUp() throws Exception {
        connector = new Connector();
        connector.connect();
    }

    @After
    public void tearDown() throws Exception {
        connector.close();
        connector = null;
    }
}
