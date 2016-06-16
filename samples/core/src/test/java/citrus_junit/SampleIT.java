package citrus_junit;

import org.junit.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestDesigner;

/**
 * TODO: Description
 *
 * @author Unknown
 */
public class SampleIT extends JUnit4CitrusTestDesigner {
  @Test
  @CitrusTest
  public void EchoSampleIT() {

    variable("time", "citrus:currentDate()");
    echo("\n\n\nHello Citrus!\n\n\n");
    echo("CurrentTime is: ${time}");
  }

  @Test
  @CitrusTest(name = "EchoIT")
  public void echoTest() {

    echo("Hello Citrus!");
  }
}