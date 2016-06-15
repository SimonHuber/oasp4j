package citrus;

import org.junit.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.junit.AbstractJUnit4CitrusTest;

/**
 * TODO: Description
 *
 * @author Unknown
 */
public class SampleIT extends AbstractJUnit4CitrusTest {
  @Test
  @CitrusTest
  public void EchoSampleIT() {

    variable("time", "citrus:currentDate()");
    echo("Hello Citrus!");
    echo("CurrentTime is: ${time}");
  }

  @Test
  @CitrusTest(name = "EchoIT")
  public void echoTest() {

    echo("Hello Citrus!");
  }
}