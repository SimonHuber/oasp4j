package io.oasp.gastronomy.restaurant.salesmanagement.logic.impl;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.general.common.SampleToCreator;
import io.oasp.gastronomy.restaurant.general.common.TestUtil;
import io.oasp.gastronomy.restaurant.general.common.base.AbstractRestServiceTest;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.Salesmanagement;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderCto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderPositionEto;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringBootApp.class })
@TestPropertySource(properties = { "flyway.locations=filesystem:src/test/resources/db/tablemanagement" })

// TODO is not clean to inherit from AbstractRestServiceTest
public class SalesmanagementAuthorisationPositiveTests extends AbstractRestServiceTest {

  @Inject
  private Salesmanagement salesmanagement;

  @Before
  public void setup() {

    getDbTestHelper().resetDatabase();
    // TODO fragen ob es nicht sinnvoll wäre auch einen Login für Testzwecke ohne Berechtigung zu speichern
    TestUtil.login("chief", PermissionConstants);
  }

  @After
  public void teardown() {

    this.salesmanagement = null;
  }

  @Test(expected = AccessDeniedException.class)
  public void saveOrder() {

    OrderCto responseOrderCto = this.salesmanagement.saveOrder(SampleToCreator.createSampleOrderCto());
    assertThat(responseOrderCto).isNotNull();
  }

  @Test(expected = AccessDeniedException.class)
  public void findOrder() {

    OrderEto responseOrderEto = this.salesmanagement.findOrder(SampleToCreator.SAMPLE_ORDER_ID);
  }

  @Test(expected = AccessDeniedException.class)
  public void saveOrderPosition() {

    OrderPositionEto responseOrderPositionEto =
        this.salesmanagement.saveOrderPosition(SampleToCreator.createSampleOrderPositionEto());
  }

  @Test(expected = AccessDeniedException.class)
  public void findOrderPosition() {

    OrderPositionEto responseOrderPositionEto =
        this.salesmanagement.findOrderPosition(SampleToCreator.SAMPLE_ORDERPOSITION_ID);
  }

  @Test(expected = AccessDeniedException.class)
  public void saveBill() {

    this.salesmanagement.createBill(SampleToCreator.createSampleBillEto());
  }

  @Test(expected = AccessDeniedException.class)
  public void findBill() {

    this.salesmanagement.findBill(SampleToCreator.SAMPLE_BILL_ID);
  }

}
