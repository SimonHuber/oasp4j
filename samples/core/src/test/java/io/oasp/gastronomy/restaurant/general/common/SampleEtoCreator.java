package io.oasp.gastronomy.restaurant.general.common;

import io.oasp.gastronomy.restaurant.common.builders.MealEtoBuilder;
import io.oasp.gastronomy.restaurant.common.builders.OfferEtoBuilder;
import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.MealEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */

// TODO evtl. in Testüberklasse injizieren
// TODO mit Jörg reden ob statisch oder injiziert
// TODO macht das Sinn die sample Attribute hier zu definieren
public class SampleEtoCreator {

  // offermanagement

  // TODO macht die Benamung Sinn?
  public static final long SAMPLE_OFFER_ID = 1;

  public static final Money SAMPLE_OFFER_PRICE = new Money(9.99);

  public static final long SAMPLE_OFFER_MEAL_ID = 1;

  public static final String SAMPLE_OFFER_MEAL_NAME = "Schnitzel";

  public static final String SAMPLE_OFFER_MEAL_DESCRIPTION = "Description of Schnitzel-Menü";

  public static final long SAMPLE_OFFER_SIDE_DISH_ID = 5;

  // TODO check if eventually not used
  public static final long SAMPLE_OFFER_DRINK_ID = 10;

  public static final String SAMPLE_OFFER_NAME = "Schnitzel-mit-Pommes";

  public static final String SAMPLE_OFFER_DESCRIPTION = "Description of Schnitzel-mit-Pommes";

  public static final OfferEto createSampleOfferEto() {

    OfferEto sampleOfferEto = new OfferEtoBuilder().price(SAMPLE_OFFER_PRICE).mealId(SAMPLE_OFFER_MEAL_ID)
        .sideDishId(SAMPLE_OFFER_SIDE_DISH_ID).name(SAMPLE_OFFER_NAME).description(SAMPLE_OFFER_DESCRIPTION)
        .createNew();
    return sampleOfferEto;
  }

  public static final MealEto createSampleMealEto() {

    MealEto sampleMealEto =
        new MealEtoBuilder().name(SAMPLE_OFFER_MEAL_NAME).description(SAMPLE_OFFER_MEAL_DESCRIPTION).createNew();
    return sampleMealEto;
  }

}
