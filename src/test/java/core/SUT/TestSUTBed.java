package core.SUT;

import org.junit.After;
import org.junit.Before;

public abstract class TestSUTBed {

    protected SUT sut;

    @Before
    public void setUp() throws Exception {
        sut = new SUT();
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }



}