package io.oasp.gastronomy.restaurant.staffmanagement.logic.impl;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.general.common.SampleCreator;
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
@TestPropertySource(properties = { "flyway.locations=filesystem:src/test/resources/db/tablemanagement" })

// TODO is not clean to inherit from AbstractRestServiceTest
public class StaffmanagementAuthorisationPositiveTests extends AbstractRestServiceTest {

  @Inject
  private Salesmanagement salesmanagement;

  @Before
  public void setup() {

    getDbTestHelper().resetDatabase();
    // TODO fragen ob es nicht sinnvoll wäre auch einen Login für Testzwecke ohne Berechtigung zu speichern
    TestUtil.login("chief", PermissionConstants.SAVE_ORDER, PermissionConstants.FIND_ORDER,
        PermissionConstants.DELETE_ORDER, PermissionConstants.SAVE_ORDER_POSITION, PermissionConstants.FIND_OFFER,
        PermissionConstants.FIND_ORDER_POSITION, PermissionConstants.SAVE_BILL, PermissionConstants.FIND_BILL,
        PermissionConstants.DELETE_BILL);
  }

  @After
  public void teardown() {

    this.salesmanagement = null;
  }

  @Test
  public void saveOrder() {

    OrderCto responseOrderCto = this.salesmanagement.saveOrder(SampleCreator.createSampleOrderCto());
    assertThat(responseOrderCto).isNotNull();
  }

  @Test
  public void findOrder() {

    OrderEto responseOrderEto = this.salesmanagement.findOrder(SampleCreator.SAMPLE_ORDER_ID);
    assertThat(responseOrderEto).isNotNull();
  }

  @Test
  public void deleteOrder() {

    OrderCto responseOrderCto = this.salesmanagement.saveOrder(SampleCreator.createSampleOrderCto());
    this.salesmanagement.deleteOrder(responseOrderCto.getOrder().getId());
    OrderEto responseOrderEto = this.salesmanagement.findOrder(responseOrderCto.getOrder().getId());
    assertThat(responseOrderEto).isNull();
  }

  // PermissionConstants.FIND_OFFER is also needed
  // TODO find out if adding PermissionConstants.FIND_OFFER to turn testresult green is wanted
  @Test
  public void saveOrderPosition() {

    OrderPositionEto responseOrderPositionEto =
        this.salesmanagement.saveOrderPosition(SampleCreator.createSampleOrderPositionEto());
    assertThat(responseOrderPositionEto).isNotNull();
  }

  @Test
  public void findOrderPosition() {

    OrderPositionEto responseOrderPositionEto =
        this.salesmanagement.findOrderPosition(SampleCreator.SAMPLE_ORDERPOSITION_ID);
    assertThat(responseOrderPositionEto).isNotNull();
  }

  @Test
  public void saveBill() {

    BillEto sampleBillEto = this.salesmanagement.createBill(SampleCreator.createSampleBillEto());
    assertThat(sampleBillEto).isNotNull();
  }

  @Test
  public void findBill() {

    BillCto sampleBillCto = this.salesmanagement.findBill(SampleCreator.SAMPLE_BILL_ID);
    assertThat(sampleBillCto).isNotNull();
  }

  @Test()
  public void deleteBill() {

    Throwable thrown = null;
    try {
      this.salesmanagement.deleteBill(SampleCreator.SAMPLE_BILL_ID);
    } catch (Throwable t) {
      thrown = t;
    }
    assertThat(thrown).isNull();
  }

}
