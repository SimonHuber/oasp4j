package citru_first_tries;

import org.junit.Test;

import com.consol.citrus.TestCaseMetaInfo.Status;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestDesigner;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */
public class BehaviourTestClass extends JUnit4CitrusTestDesigner {
  @Test
  @CitrusTest
  public void behaviorTest(@CitrusResource TestContext context) {

    description("\n\n\n\nThis is a behavior Test\n\n\n\n");
    status(Status.FINAL);

    author("\n\n\n\nChristoph\n\n\n\n");
    // status(TestCaseMetaInfo.Status.FINAL);
    // echo("\n\n\n\n--------------------------\n\n\n\n");
    variable("testVar", "varBefore");
    // echo("\n\n\n\n--------------------------\n\n\n\n");
    // echo("\n\n\ntestVarBefore: ${testVar}\n\n\n");

    applyBehavior(new FooBehavior());
    echo("\n\n\n\nSuccessfully applied foo behavior\n\n\n\n");
    applyBehavior(new BarBehavior());
    echo("Successfully applied bar behavior");
    echo("\n\n\nuserName:    ${userName2}\n\n\n");
    // because of the TestDesigner's feature to build up the test first, only the value "varAfter" is printed
    variable("testVar", "varAfter");
    echo("testVarAfter:    ${testVar}");
  }
}
