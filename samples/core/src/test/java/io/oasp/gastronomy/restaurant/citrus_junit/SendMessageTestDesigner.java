package io.oasp.gastronomy.restaurant.citrus_junit;

import org.junit.Test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestDesigner;

public class SendMessageTestDesigner extends JUnit4CitrusTestDesigner {
  @Test
  @CitrusTest(name = "SendMessageTest")
  public void sendMessageTest() {

    // TODO ?? why global variables do not work
    // echo("\n\n\nprojectName: ${userName}\n\n\n");

    description("Basic send message example");
    send("helloServiceEndpoint").payload("<TestMessage>" + "<Text>Hello!</Text>" + "</TestMessage>").header("Operation",
        "sayHello");
  }
}