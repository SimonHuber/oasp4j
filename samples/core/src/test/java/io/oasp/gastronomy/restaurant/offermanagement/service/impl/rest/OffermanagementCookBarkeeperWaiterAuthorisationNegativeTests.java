package io.oasp.gastronomy.restaurant.offermanagement.service.impl.rest;

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
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;
import io.oasp.gastronomy.restaurant.offermanagement.service.api.rest.OffermanagementRestService;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringBootApp.class })

public class OffermanagementCookBarkeeperWaiterAuthorisationNegativeTests extends AbstractRestServiceTest {

  private OffermanagementRestService offermanagementService;

  private final String ROLE = "cook";

  @Before
  public void init() {

    getDbTestHelper().resetDatabase();
    this.offermanagementService = getRestTestClientBuilder().build(OffermanagementRestService.class, this.ROLE);
  }

  @After
  public void teardown() {

    this.offermanagementService = null;
  }

  @Test(expected = ForbiddenException.class)
  public void saveOffer() {

    OfferEto responseOfferEto = this.offermanagementService.saveOffer(SampleCreator.createSampleOfferEto());
    assertThat(responseOfferEto).isNotNull();

  }

  @Test(expected = ForbiddenException.class)
  public void deleteOffer() {

    this.offermanagementService.deleteOffer(SampleCreator.SAMPLE_OFFER_ID);
  }

  @Test(expected = ForbiddenException.class)
  public void saveProduct() {

    ProductEto responseProductEto = this.offermanagementService.saveProduct(SampleCreator.createSampleMealEto());
  }

  @Test(expected = ForbiddenException.class)
  public void deleteProduct() {

    this.offermanagementService.deleteProduct(SampleCreator.SAMPLE_OFFER_MEAL_ID);
  }

  // TODO write that productpicture is left out
}
