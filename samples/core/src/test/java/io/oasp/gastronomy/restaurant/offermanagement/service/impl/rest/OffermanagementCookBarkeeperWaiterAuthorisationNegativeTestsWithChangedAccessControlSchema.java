package io.oasp.gastronomy.restaurant.offermanagement.service.impl.rest;

import javax.ws.rs.ForbiddenException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.common.test.SampleCreator;
import io.oasp.gastronomy.restaurant.general.common.base.AbstractRestServiceTest;
import io.oasp.gastronomy.restaurant.general.configuration.WebSecurityBeansConfig;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.ProductEto;
import io.oasp.gastronomy.restaurant.offermanagement.service.api.rest.OffermanagementRestService;
import io.oasp.gastronomy.restaurant.offermanagement.service.impl.rest.OffermanagementCookBarkeeperWaiterAuthorisationNegativeTestsWithChangedAccessControlSchema.TempWebSecurityBean;
import io.oasp.module.security.common.api.accesscontrol.AccessControlProvider;
import io.oasp.module.security.common.base.accesscontrol.AccessControlSchemaProvider;
import io.oasp.module.security.common.impl.accesscontrol.AccessControlProviderImpl;
import io.oasp.module.security.common.impl.accesscontrol.AccessControlSchemaProviderImpl;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringBootApp.class, TempWebSecurityBean.class })

public class OffermanagementCookBarkeeperWaiterAuthorisationNegativeTestsWithChangedAccessControlSchema
    extends AbstractRestServiceTest {
  final static String locationPrefix = "config/app/security/";

  private OffermanagementRestService offermanagementService;

  private final String ROLE = "barkeeper";

  @Before
  public void init() {

    getDbTestHelper().resetDatabase();
    this.offermanagementService = getRestTestClientBuilder().build(OffermanagementRestService.class, this.ROLE);

  }

  @After
  public void teardown() {

    this.offermanagementService = null;
  }

  private AccessControlProvider createProvider(String location) {

    ClassPathResource resource = new ClassPathResource(location);
    AccessControlProviderImpl accessControlProvider = new AccessControlProviderImpl();
    AccessControlSchemaProviderImpl accessControlSchemaProvider = new AccessControlSchemaProviderImpl();
    accessControlSchemaProvider.setAccessControlSchema(resource);
    accessControlProvider.setAccessControlSchemaProvider(accessControlSchemaProvider);
    accessControlProvider.initialize();
    return accessControlProvider;
  }

  private AccessControlSchemaProvider createAccessControlSchemaProvider(String location) {

    ClassPathResource resource = new ClassPathResource(location);
    AccessControlSchemaProviderImpl accessControlSchemaProvider = new AccessControlSchemaProviderImpl();
    accessControlSchemaProvider.setAccessControlSchema(resource);
    return accessControlSchemaProvider;
  }

  public static class TempWebSecurityBean extends WebSecurityBeansConfig {

    @Override
    @Bean
    public AccessControlSchemaProvider accessControlSchemaProvider() {

      ClassPathResource resource = new ClassPathResource(locationPrefix + "changed_access-control-schema.xml");
      AccessControlSchemaProviderImpl accessControlSchemaProvider = new AccessControlSchemaProviderImpl();
      accessControlSchemaProvider.setAccessControlSchema(resource);
      return accessControlSchemaProvider;
    }
  }

  @Test
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
