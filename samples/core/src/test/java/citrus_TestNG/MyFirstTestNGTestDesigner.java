package citrus_TestNG;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */

import org.testng.annotations.Test;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;

@Test
@RunWith(TestNGCitrusTestDesigner.class)
public class MyFirstTestNGTestDesigner extends TestNGCitrusTestDesigner {
@CitrusTest(name = "MyFirstTest")
public void myFirstTest() {
description("First example showing the basic test case definition elements!");
variable("text", "Hello Test Framework");
echo("${text}");
}
}