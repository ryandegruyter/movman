package ryandg.com.movieman;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ryan De Gruyter on 16/05/2015.
 *
 */
@SmallTest
public class SimpleTest {

    private String expected;

    @Before public void init() {
        expected = "Hello";
    }

    @Test public void equalsHello(){
        Assert.assertEquals(expected,"Hello");
    }
}
