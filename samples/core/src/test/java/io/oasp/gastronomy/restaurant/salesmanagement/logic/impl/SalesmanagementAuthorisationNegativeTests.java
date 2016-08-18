package io.oasp.gastronomy.restaurant.salesmanagement.logic.impl;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.common.test.SampleCreator;
import io.oasp.gastronomy.restaurant.general.common.TestUtil;
import io.oasp.gastronomy.restaurant.general.common.api.constants.PermissionConstants;
import io.oasp.gastronomy.restaurant.general.common.base.AbstractRestServiceTest;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.Salesmanagement;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.BillCto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.BillEto;
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

// TODO is not clean to inherit from AbstractRestServiceTest
public class SalesmanagementAuthorisationNegativeTests extends AbstractRestServiceTest {

  @Inject
  private Salesmanagement salesmanagement;

  @Before
  public void setup() {

    getDbTestHelper().resetDatabase();
    // TODO fragen ob es nicht sinnvoll wäre auch einen Login für Testzwecke ohne Berechtigung zu speichern
    // TODO PermissionConstants.DELETE_ORDER, PermissionConstants.DELETE_ORDER_POSITION
    TestUtil.login("chief", PermissionConstants.DELETE_ORDER);
  }

  @After
  public void teardown() {

    this.salesmanagement = null;
  }

  @Test(expected = AccessDeniedException.class)
  public void saveOrder() {

    OrderCto responseOrderCto = this.salesmanagement.saveOrder(SampleCreator.createSampleOrderCto());
  }

  @Test(expected = AccessDeniedException.class)
  public void findOrder() {

    OrderEto responseOrderEto = this.salesmanagement.findOrder(SampleCreator.SAMPLE_ORDER_ID);
  }

  @Test(expected = AccessDeniedException.class)
  public void saveOrderPosition() {

    OrderPositionEto responseOrderPositionEto =
        this.salesmanagement.saveOrderPosition(SampleCreator.createSampleOrderPositionEto());
  }

  @Test(expected = AccessDeniedException.class)
  public void findOrderPosition() {

    OrderPositionEto responseOrderPositionEto =
        this.salesmanagement.findOrderPosition(SampleCreator.SAMPLE_ORDERPOSITION_ID);
  }

  @Test(expected = AccessDeniedException.class)
  public void saveBill() {

    BillEto sampleBillEto = this.salesmanagement.createBill(SampleCreator.createSampleBillEto());
  }

  @Test(expected = AccessDeniedException.class)
  public void findBill() {

    BillCto sampleBillCto = this.salesmanagement.findBill(SampleCreator.SAMPLE_BILL_ID);
  }

  @Test(expected = AccessDeniedException.class)
  public void deleteBill() {

    this.salesmanagement.deleteBill(SampleCreator.SAMPLE_BILL_ID);
  }

}
