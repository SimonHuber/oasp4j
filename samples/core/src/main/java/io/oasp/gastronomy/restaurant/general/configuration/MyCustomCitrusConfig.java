package io.oasp.gastronomy.restaurant.general.configuration;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */
import com.consol.citrus.TestCase;
import com.consol.citrus.report.AbstractTestListener;
import com.consol.citrus.report.TestListener;
import com.consol.citrus.report.TestReporter;

import io.oasp.module.batch.common.base.SpringBootBatchCommandLine;

@Configuration
public class MyCustomCitrusConfig {
  @Bean(name = "customTestListener")
  public TestListener customTestListener() {

    return new PlusMinusTestReporter();
  }

  private static class PlusMinusTestReporter extends AbstractTestListener implements TestReporter {
    /** Logger */
    private Logger log = LoggerFactory.getLogger(SpringBootBatchCommandLine.class);

    private StringBuilder testReport = new StringBuilder();

    @Override
    public void onTestSuccess(TestCase test) {
    }

    @Override
    public void onTestFailure(TestCase test, Throwable cause) {

      this.testReport.append("-");
    }

    @Override
    public void generateTestResults() {

      this.log.info(this.testReport.toString());
    }

    @Override
    public void clearTestResults() {

      this.testReport = new StringBuilder();
    }
  }
}