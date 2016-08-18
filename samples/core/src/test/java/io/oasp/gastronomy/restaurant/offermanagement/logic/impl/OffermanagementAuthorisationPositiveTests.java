package io.oasp.gastronomy.restaurant.offermanagement.logic.impl;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.common.test.SampleCreator;
import io.oasp.gastronomy.restaurant.general.common.TestUtil;
import io.oasp.gastronomy.restaurant.general.common.api.constants.PermissionConstants;
import io.oasp.gastronomy.restaurant.general.common.base.AbstractRestServiceTest;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.Offermanagement;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;

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
public class OffermanagementAuthorisationPositiveTests extends AbstractRestServiceTest {

  @Inject
  private Offermanagement offermanagement;

  @Before
  public void setup() {

    getDbTestHelper().resetDatabase();
    // TODO fragen ob es nicht sinnvoll wäre auch einen Login für Testzwecke ( PermissionConstants.DELETE_ORDER) ohne
    // Berechtigung zu speichern
    TestUtil.login("chief", PermissionConstants.FIND_OFFER, PermissionConstants.SAVE_OFFER,
        PermissionConstants.DELETE_OFFER, PermissionConstants.FIND_PRODUCT, PermissionConstants.SAVE_PRODUCT,
        PermissionConstants.DELETE_PRODUCT, PermissionConstants.FIND_PRODUCT_PICTURE);
  }

  @After
  public void teardown() {

    this.offermanagement = null;
  }

  @Test
  public void findOffer() {

    OfferEto responseOfferEto = this.offermanagement.findOffer(SampleCreator.SAMPLE_OFFER_ID);
    assertThat(responseOfferEto).isNotNull();
  }

  @Test
  public void saveOffer() {

    OfferEto responseOfferEto = this.offermanagement.saveOffer(SampleCreator.createSampleOfferEto());
    assertThat(responseOfferEto).isNotNull();
  }

  @Test
  public void deleteOffer() {

    this.offermanagement.deleteOffer(SampleCreator.SAMPLE_OFFER_ID);
  }

  @Test
  public void findProduct() {

    ProductEto responseProductEto = this.offermanagement.findProduct(SampleCreator.SAMPLE_OFFER_MEAL_ID);
    assertThat(responseProductEto).isNotNull();
  }

  @Test
  public void saveProduct() {

    ProductEto responseProductEto = this.offermanagement.saveProduct(SampleCreator.createSampleMealEto());
    assertThat(responseProductEto).isNotNull();
  }

  @Test(expected = DataIntegrityViolationException.class)
  public void deleteProduct() {

    this.offermanagement.deleteProduct(SampleCreator.SAMPLE_OFFER_MEAL_ID);
  }

  // TODO Jörg fragen, ob man überhaupt auf Picture Testen soll, weil nix in der Datenbank steht
  @Test
  public void findProductPicture() {

    // BinaryObjectEto responseProductPictureEto = this.offermanagement.findProductPicture(1L);
    // assertThat(responseProductPictureEto).isNotNull();
  }

}
