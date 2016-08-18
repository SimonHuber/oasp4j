package io.oasp.gastronomy.restaurant.tablemanagement.logic.impl;

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
import io.oasp.gastronomy.restaurant.tablemanagement.logic.api.Tablemanagement;
import io.oasp.gastronomy.restaurant.tablemanagement.logic.api.to.TableEto;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringBootApp.class })

// TODO is not clean to inherit from AbstractRestServiceTest
public class TablemanagementAuthorisationNegativeTests extends AbstractRestServiceTest {

  @Inject
  private Tablemanagement tablemanagement;

  @Before
  public void setup() {

    getDbTestHelper().resetDatabase();
    // TODO fragen ob es nicht sinnvoll wäre auch einen Login für Testzwecke ohne Berechtigung zu speichern
    // TODO PermissionConstants.DELETE_ORDER, PermissionConstants.DELETE_ORDER_POSITION
    TestUtil.login("chief", PermissionConstants.DELETE_ORDER);
  }

  @After
  public void teardown() {

    this.tablemanagement = null;
  }

  @Test(expected = AccessDeniedException.class)
  public void findTable() {

    TableEto responseTableEto = this.tablemanagement.findTable(SampleCreator.SAMPLE_TABLE_ID);
    assertThat(responseTableEto).isNotNull();
  }

  @Test(expected = AccessDeniedException.class)
  public void saveTable() {

    TableEto responseTableEto = this.tablemanagement.saveTable(SampleCreator.createSampleTableEto());
    assertThat(responseTableEto).isNotNull();
  }

  @Test(expected = AccessDeniedException.class)
  public void deleteTable() {

    this.tablemanagement.deleteTable(SampleCreator.SAMPLE_TABLE_ID);
  }

}
