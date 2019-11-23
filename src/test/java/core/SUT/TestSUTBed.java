package core.SUT;

import core.model.Estado;
import core.model.Tiempo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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