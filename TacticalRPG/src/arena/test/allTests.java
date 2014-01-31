package arena.test;

import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ kaetonTest.class, octorockTest.class, linkTest.class, levelOneTest.class, waveTest.class, audioReadtest.class })
public class allTests extends TestSuite {
}

