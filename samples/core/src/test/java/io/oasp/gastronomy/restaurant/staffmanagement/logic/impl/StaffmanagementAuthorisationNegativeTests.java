package io.oasp.gastronomy.restaurant.staffmanagement.logic.impl;

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
import io.oasp.gastronomy.restaurant.staffmanagement.logic.api.Staffmanagement;
import io.oasp.gastronomy.restaurant.staffmanagement.logic.api.to.StaffMemberEto;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringBootApp.class })

// TODO is not clean to inherit from AbstractRestServiceTest
public class StaffmanagementAuthorisationNegativeTests extends AbstractRestServiceTest {

  @Inject
  private Staffmanagement staffmanagement;

  @Before
  public void setup() {

    getDbTestHelper().resetDatabase();
    // TODO fragen ob es nicht sinnvoll wäre auch einen Login für Testzwecke ohne Berechtigung zu speichern
    // TODO PermissionConstants.DELETE_ORDER, PermissionConstants.DELETE_ORDER_POSITION
    TestUtil.login("chief", PermissionConstants.DELETE_ORDER);
  }

  @After
  public void teardown() {

    this.staffmanagement = null;
  }

  @Test(expected = AccessDeniedException.class)
  public void findStaffMember() {

    StaffMemberEto responseStaffMemberEto = this.staffmanagement.findStaffMember(SampleCreator.SAMPLE_STAFF_MEMBER_ID);
    assertThat(responseStaffMemberEto).isNotNull();
  }

  @Test(expected = AccessDeniedException.class)
  public void saveStaffMember() {

    StaffMemberEto responseStaffMemberEto =
        this.staffmanagement.saveStaffMember(SampleCreator.createSampleStaffMemberEto());
    assertThat(responseStaffMemberEto).isNotNull();
  }

  @Test(expected = AccessDeniedException.class)
  public void deleteStaffMember() {

    this.staffmanagement.deleteStaffMember(SampleCreator.SAMPLE_STAFF_MEMBER_ID);
  }

}
