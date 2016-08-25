package io.oasp.gastronomy.restaurant.staffmanagement.service.impl.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.common.builders.StaffMemberEtoBuilder;
import io.oasp.gastronomy.restaurant.general.common.api.datatype.Role;
import io.oasp.gastronomy.restaurant.general.common.base.AbstractRestServiceTest;
import io.oasp.gastronomy.restaurant.salesmanagement.service.api.rest.SalesmanagementRestService;
import io.oasp.gastronomy.restaurant.staffmanagement.logic.api.to.StaffMemberEto;
import io.oasp.gastronomy.restaurant.staffmanagement.service.api.rest.StaffmanagementRestService;

/**
 * This class serves as an example of how to perform a subsystem test (e.g., call a *RestService interface). The test
 * database is accessed via an instance of the class {@link SalesmanagementRestService}.
 *
 * @author shuber
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringBootApp.class })

public class StaffmanagementRestServiceTest extends AbstractRestServiceTest {

  private StaffmanagementRestService service;

  @Override
  public void doSetUp() {

    super.doSetUp();
    this.service = getRestTestClientBuilder().build(StaffmanagementRestService.class);
  }

  @Override
  public void doTearDown() {

    super.doTearDown();
    this.service = null;
  }

  // TODO evenutally needs to be updated due to sample value creation
  /**
   * This test method tests whether the modificationCounter is updated correctly.
   */
  @Test
  public void testUnchangedStaffMemberModificationCounter() {

    // given
    StaffMemberEto sampleStaffmemberEto =
        new StaffMemberEtoBuilder().firstName("Claus").lastName("Chef").role(Role.CHIEF).createNew();
    StaffMemberEto responseStaffmemberEto = this.service.saveStaffMember(sampleStaffmemberEto);
    assertThat(responseStaffmemberEto).isNotNull();

    int initialModificationCounter = responseStaffmemberEto.getModificationCounter();

    // when
    responseStaffmemberEto = this.service.saveStaffMember(responseStaffmemberEto);
    assertThat(responseStaffmemberEto).isNotNull();

    // then
    assertThat(responseStaffmemberEto.getModificationCounter()).isEqualTo(initialModificationCounter);
  }

  // TODO evenutally needs to be updated due to sample value creation
  /**
   * This test method tests whether the modificationCounter is updated correctly.
   */
  @Test
  public void testChangedStaffMemberModificationCounter() {

    // given
    StaffMemberEto sampleStaffmemberEto =
        new StaffMemberEtoBuilder().firstName("Claus").lastName("Chef").role(Role.CHIEF).createNew();
    StaffMemberEto responseStaffmemberEto = this.service.saveStaffMember(sampleStaffmemberEto);
    assertThat(responseStaffmemberEto).isNotNull();

    int initialModificationCounter = responseStaffmemberEto.getModificationCounter();

    // when
    responseStaffmemberEto.setLastName("Chief-Chef");
    responseStaffmemberEto = this.service.saveStaffMember(responseStaffmemberEto);
    assertThat(responseStaffmemberEto).isNotNull();

    // then
    assertThat(responseStaffmemberEto.getModificationCounter()).isEqualTo(initialModificationCounter + 1);
  }

}