package io.oasp.gastronomy.restaurant.general.common;

import java.util.ArrayList;

import io.oasp.gastronomy.restaurant.common.builders.BillEtoBuilder;
import io.oasp.gastronomy.restaurant.common.builders.MealEtoBuilder;
import io.oasp.gastronomy.restaurant.common.builders.OfferEtoBuilder;
import io.oasp.gastronomy.restaurant.common.builders.OrderCtoBuilder;
import io.oasp.gastronomy.restaurant.common.builders.OrderEtoBuilder;
import io.oasp.gastronomy.restaurant.common.builders.OrderPositionEtoBuilder;
import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.DrinkEntity;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.MealEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.salesmanagement.dataaccess.api.BillEntity;
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

public class SampleCreator {

  // offermanagement

  // TODO macht die Benamung Sinn?
  public static final long SAMPLE_OFFER_ID = 1;

  public static final Money SAMPLE_OFFER_PRICE = new Money(9.99);

  public static final long SAMPLE_MEAL_ID = 1;

  public static final String SAMPLE_MEAL_DESCRIPTION = "Schnitzel";

  public static final long SAMPLE_SIDEDISH_ID = 7;

  public static final String NEW_OFFER_NAME = "Reisschnitzel-Menü";

  public static final String NEW_OFFER_DESCRIPTION = "Reisschnitzel-Menü";

  public static final long NEW_OFFER_MEAL_ID = 1;

  public static final long NEW_OFFER_SIDEDISH_ID = 8;

  public static final long NEW_OFFER_DRINK_ID = 11;

  public static final double NEW_OFFER_PRICE = 7.19;

  public static final String SAMPLE_OFFER_NAME = "Schnitzel-Menü";

  public static final String SAMPLE_OFFER_DESCRIPTION = "Description of Schnitzel-Menü";

  public static final long SAMPLE_OFFER_MEAL_ID = 1;

  public static final long SAMPLE_OFFER_SIDEDISH_ID = 7;

  public static final long SAMPLE_OFFER_DRINK_ID = 12;

  public static final int NUMBER_OF_MEALS = 6;

  public static final int NUMBER_OF_SIDEDISHES = 4;

  public static final int NUMBER_OF_DRINKS = 4;

  public static final boolean SAMPLE_ALCOHOLIC_FLAG = false;

  public static final String SAMPLE_DRINK_NAME = "Spring Paradise";

  public static final String SAMPLE_DRINK_DESCRIPTION = "without alcohol";

  // salesmanagement
  public static final long SAMPLE_ORDER_ID = 1;

  public static final long SAMPLE_ORDERPOSITION_ID = 1;

  public static final double SAMPLE_ORDERPOSITION_PRICE = 1.2;

  public static final String SAMPLE_ORDER_COMMENT = "mit Ketchup";

  public static final long SAMPLE_BILL_ID = 1;

  public static final double SAMPLE_TOTAL = 42.42;

  public static final double SAMPLE_TIP = 1.0;

  public static final boolean SAMPLE_PAYED_FLAG = true;

  public static final long SAMPLE_TABLE_ID = 101;

  // offermanagement
  public static final OfferEto createSampleOfferEto() {

    OfferEto sampleOfferEto = new OfferEtoBuilder().price(NEW_OFFER_PRICE).mealId(NEW_OFFER_MEAL_ID)
        .sideDishId(NEW_OFFER_SIDE_DISH_ID).name(NEW_OFFER_NAME).description(NEW_OFFER_DESCRIPTION).createNew();
    return sampleOfferEto;
  }

  public static final MealEto createSampleMealEto() {

    MealEto sampleMealEto =
        new MealEtoBuilder().name(SAMPLE_OFFER_MEAL_NAME).description(SAMPLE_OFFER_MEAL_DESCRIPTION).createNew();
    return sampleMealEto;
  }

  // salesmanagement
  public static final OrderEto createSampleOrderEto() {

    OrderEto sampleOrderEto = new OrderEtoBuilder().tableId(SAMPLE_TABLE_ID).createNew();
    return sampleOrderEto;
  }

  public static final OrderCto createSampleOrderCto() {

    OrderCto sampleOrderCto = new OrderCtoBuilder().order(createSampleOrderEto()).createNew();
    return sampleOrderCto;
  }

  public static final OrderPositionEto createSampleOrderPositionEto() {

    OrderPositionEto sampleOrderPositionEto = createSampleOrderPositionEto(SAMPLE_ORDER_ID);
    return sampleOrderPositionEto;
  }

  public static final OrderPositionEto createSampleOrderPositionEto(long orderId) {

    OrderPositionEto sampleOrderPositionEto =
        new OrderPositionEtoBuilder().orderId(orderId).offerId(SAMPLE_OFFER_ID).comment(SAMPLE_ORDER_COMMENT)
            .offerName(SAMPLE_OFFER_NAME).price(new Money(SAMPLE_ORDERPOSITION_PRICE)).createNew();
    return sampleOrderPositionEto;
  }

  public static final BillEto createSampleBillEto() {

    ArrayList sampeOrderPositionIdList = new ArrayList<Long>();
    sampeOrderPositionIdList.add(SAMPLE_ORDERPOSITION_ID);
    BillEto sampleBillEto = new BillEtoBuilder().orderPositionIds(sampeOrderPositionIdList)
        .total(new Money(SAMPLE_TOTAL)).tip(new Money(SAMPLE_TIP)).payed(SAMPLE_PAYED_FLAG).createNew();
    return sampleBillEto;
  }

  public static final BillEntity createSampleBillEntity() {

    ArrayList sampeOrderPositionIdList = new ArrayList<Long>();
    sampeOrderPositionIdList.add(SAMPLE_ORDERPOSITION_ID);
    BillEntity sampleBillEntity = new BillEntityBuilder().orderPositionIds(sampeOrderPositionIdList)
        .total(new Money(SAMPLE_TOTAL)).tip(new Money(SAMPLE_TIP)).payed(SAMPLE_PAYED_FLAG).createNew();
    return sampleBillEntity;
  }

  public static final DrinkEntity createSampleDrinkEntity() {

    DrinkEntity sampleDrinkEntity = new DrinkEntityBuilder().alcoholic(SAMPLE_ALCOHOLIC_FLAG)
        .description(SAMPLE_DRINK_DESCRIPTION).name(SAMPLE_DRINK_NAME).createNew();
    return sampleDrinkEntity;
  }

}
