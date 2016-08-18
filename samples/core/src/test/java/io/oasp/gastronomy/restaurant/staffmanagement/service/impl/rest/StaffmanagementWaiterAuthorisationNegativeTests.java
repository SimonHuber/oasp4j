package io.oasp.gastronomy.restaurant.staffmanagement.service.impl.rest;

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
import io.oasp.gastronomy.restaurant.staffmanagement.service.api.rest.StaffmanagementRestService;

//cook and barkeeper have the same authorisation in respect to staffmanagement
/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringBootApp.class })

public class StaffmanagementWaiterAuthorisationNegativeTests extends AbstractRestServiceTest {

  private StaffmanagementRestService staffmanagementService;

  private final String ROLE = "cook";

  @Before
  public void init() {

    getDbTestHelper().resetDatabase();
    this.staffmanagementService = getRestTestClientBuilder().build(StaffmanagementRestService.class, this.ROLE);
  }

  @After
  public void teardown() {

    this.staffmanagementService = null;
  }

  @Test(expected = ForbiddenException.class)
  public void saveStaffMember() {

    this.staffmanagementService.saveStaffMember(SampleCreator.createSampleStaffMemberEto());
  }

  @Test(expected = ForbiddenException.class)
  public void deleteStaffMember() {

    this.staffmanagementService.deleteStaffMember("chief");
  }
}
