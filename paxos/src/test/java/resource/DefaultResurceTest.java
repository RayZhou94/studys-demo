package resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultResurceTest {

    private DefaultResurce resurce;

    @Before
    public void setUp() throws Exception {
        resurce = new DefaultResurce();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getServerInfo() {
        ServerBean serverBean = resurce.getServerInfo("server1.yaml");
        Assert.assertTrue(serverBean != null);
    }
}