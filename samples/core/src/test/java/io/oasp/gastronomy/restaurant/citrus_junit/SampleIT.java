package io.oasp.gastronomy.restaurant.citrus_junit;

import org.junit.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestDesigner;

import io.oasp.gastronomy.restaurant.general.configuration.MyCustomCitrusConfig;

/**
 * TODO: Description
 *
 * @author Unknown
 */
// @SpringApplicationConfiguration(classes = { SpringBootApp.class, MyCustomCitrusConfig.class })

public class SampleIT extends JUnit4CitrusTestDesigner {

  // @Inject
  // private TestListener customTestListener;

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

    System.setProperty("citrus.spring.java.config", MyCustomCitrusConfig.class.getName());
    echo("Hello Citrus!");
  }
}