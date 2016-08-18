package io.oasp.gastronomy.restaurant.tablemanagement.service.impl.rest;

import javax.ws.rs.ForbiddenException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.common.test.SampleCreator;
import io.oasp.gastronomy.restaurant.general.common.base.AbstractRestServiceTest;
import io.oasp.gastronomy.restaurant.tablemanagement.service.api.rest.TablemanagementRestService;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringBootApp.class })

public class TablemanagementCookBarkeeperAuthorisationNegativeTests extends AbstractRestServiceTest {

  private TablemanagementRestService tablemanagementService;

  private final String ROLE = "cook";

  @Before
  public void init() {

    getDbTestHelper().resetDatabase();
    this.tablemanagementService = getRestTestClientBuilder().build(TablemanagementRestService.class, this.ROLE);

  }

  @After
  public void teardown() {

    this.tablemanagementService = null;
  }

  @Test(expected = ForbiddenException.class)
  public void saveTable() {

    this.tablemanagementService.saveTable(SampleCreator.createSampleTableEto());
  }

  @Test(expected = ForbiddenException.class)
  public void deleteTable() {

    this.tablemanagementService.deleteTable(SampleCreator.SAMPLE_TABLE_ID);
  }

}
