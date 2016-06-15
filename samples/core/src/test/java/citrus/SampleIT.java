package citrus;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.JUnit4CitrusTestDesigner;
import org.junit.Test;
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
echo("Hello Citrus!");
echo("CurrentTime is: ${time}");
}
@Test
@CitrusTest(name = "EchoIT")
public void echoTest() {
echo("Hello Citrus!");
}
}