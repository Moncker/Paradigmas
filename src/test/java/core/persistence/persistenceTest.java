package core.persistence;

import org.junit.After;
import org.junit.Before;


public abstract class persistenceTest {

    protected ConnectorStore connectorStore;

    @Before
    public void setUp() throws Exception {
        connectorStore = new ConnectorStore();
        connectorStore.connect();
    }

    @After
    public void tearDown() throws Exception {
        connectorStore.close();
        connectorStore = null;
    }
}
