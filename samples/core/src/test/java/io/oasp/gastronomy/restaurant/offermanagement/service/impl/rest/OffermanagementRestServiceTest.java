package io.oasp.gastronomy.restaurant.offermanagement.service.impl.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.common.builders.MealEtoBuilder;
import io.oasp.gastronomy.restaurant.common.builders.OfferEtoBuilder;
import io.oasp.gastronomy.restaurant.general.common.base.AbstractRestServiceTest;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.datatype.OfferState;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;
import io.oasp.gastronomy.restaurant.offermanagement.service.api.rest.OffermanagementRestService;
import io.oasp.gastronomy.restaurant.salesmanagement.service.api.rest.SalesmanagementRestService;

/**
 * This class serves as an example of how to perform a subsystem test (e.g., call a *RestService interface). The test
 * database is accessed via an instance of the class {@link SalesmanagementRestService}.
 *
 * @author shuber
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringBootApp.class })

public class OffermanagementRestServiceTest extends AbstractRestServiceTest {

  private OffermanagementRestService service;

  @Override
  public void doSetUp() {

    super.doSetUp();
    this.service = getRestTestClientBuilder().build(OffermanagementRestService.class);
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
  public void testUnchangedOfferEtoModificationCounter() {

    // given
    OfferEto sampleOfferEto = new OfferEtoBuilder().mealId(1L).createNew();
    OfferEto responseOfferEto = this.service.saveOffer(sampleOfferEto);
    assertThat(responseOfferEto).isNotNull();

    int initialModificationCounter = responseOfferEto.getModificationCounter();

    // when
    responseOfferEto = this.service.saveOffer(responseOfferEto);
    assertThat(responseOfferEto).isNotNull();

    // then
    assertThat(responseOfferEto.getModificationCounter()).isEqualTo(initialModificationCounter);
  }

  // TODO evenutally needs to be updated due to sample value creation
  /**
   * This test method tests whether the modificationCounter is updated correctly.
   */
  @Test
  public void testChangedOfferEtoModificationCounter() {

    // given
    OfferEto sampleOfferEto = new OfferEtoBuilder().mealId(1L).createNew();
    OfferEto responseOfferEto = this.service.saveOffer(sampleOfferEto);
    assertThat(responseOfferEto).isNotNull();

    int initialModificationCounter = responseOfferEto.getModificationCounter();

    // when
    responseOfferEto.setState(OfferState.SOLDOUT);
    responseOfferEto = this.service.saveOffer(responseOfferEto);
    assertThat(responseOfferEto).isNotNull();

    // then
    assertThat(responseOfferEto.getModificationCounter()).isEqualTo(initialModificationCounter + 1);
  }

  // TODO evenutally needs to be updated due to sample value creation
  /**
   * This test method tests whether the modificationCounter is updated correctly.
   */
  @Test
  public void testUnchangedProductEtoModificationCounter() {

    // given
    ProductEto sampleMealEto = new MealEtoBuilder().createNew();
    ProductEto responseMealEto = this.service.saveProduct(sampleMealEto);
    assertThat(responseMealEto).isNotNull();

    int initialModificationCounter = responseMealEto.getModificationCounter();

    // when
    responseMealEto = this.service.saveProduct(responseMealEto);
    assertThat(responseMealEto).isNotNull();

    // then
    assertThat(responseMealEto.getModificationCounter()).isEqualTo(initialModificationCounter);
  }

  // TODO evenutally needs to be updated due to sample value creation
  /**
   * This test method tests whether the modificationCounter is updated correctly.
   */
  @Test
  public void testChangedProductEtoModificationCounter() {

    // given
    ProductEto sampleMealEto = new MealEtoBuilder().createNew();
    ProductEto responseMealEto = this.service.saveProduct(sampleMealEto);
    assertThat(responseMealEto).isNotNull();

    int initialModificationCounter = responseMealEto.getModificationCounter();

    // when
    responseMealEto.setName("newName");
    responseMealEto = this.service.saveProduct(responseMealEto);
    assertThat(responseMealEto).isNotNull();

    // then
    assertThat(responseMealEto.getModificationCounter()).isEqualTo(initialModificationCounter + 1);
  }
}