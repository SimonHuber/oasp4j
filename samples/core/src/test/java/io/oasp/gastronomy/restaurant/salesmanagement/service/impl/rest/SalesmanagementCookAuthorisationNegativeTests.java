package io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest;

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
import io.oasp.gastronomy.restaurant.salesmanagement.service.api.rest.SalesmanagementRestService;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringBootApp.class })

public class SalesmanagementCookAuthorisationNegativeTests extends AbstractRestServiceTest {

  private SalesmanagementRestService salesmanagementService;

  private final String ROLE = "cook";

  @Before
  public void init() {

    getDbTestHelper().resetDatabase();
    this.salesmanagementService = getRestTestClientBuilder().build(SalesmanagementRestService.class, this.ROLE);
  }

  @After
  public void teardown() {

    this.salesmanagementService = null;
  }

  // these permissions are not granted to the cook
  @Test(expected = ForbiddenException.class)
  public void saveBill() {

    this.salesmanagementService.createBill(SampleCreator.createSampleBillEto());
  }

  @Test(expected = ForbiddenException.class)
  public void findBill() {

    this.salesmanagementService.findBill(SampleCreator.SAMPLE_BILL_ID);
  }

  @Test(expected = ForbiddenException.class)
  public void deleteBill() {

    this.salesmanagementService.findBill(SampleCreator.SAMPLE_BILL_ID);
  }
}
