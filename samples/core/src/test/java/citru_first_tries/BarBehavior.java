package citru_first_tries;

import com.consol.citrus.dsl.design.AbstractTestBehavior;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */
public class BarBehavior extends AbstractTestBehavior {
  @Override
  public void apply() {

    variable("testVar", "bar");
    echo("barBehavior");
    echo("${testVar}");
  }
}