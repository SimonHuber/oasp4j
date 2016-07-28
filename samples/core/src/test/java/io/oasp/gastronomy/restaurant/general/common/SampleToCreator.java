package io.oasp.gastronomy.restaurant.general.common;

import java.util.ArrayList;

import io.oasp.gastronomy.restaurant.common.builders.BillEtoBuilder;
import io.oasp.gastronomy.restaurant.common.builders.MealEtoBuilder;
import io.oasp.gastronomy.restaurant.common.builders.OfferEtoBuilder;
import io.oasp.gastronomy.restaurant.common.builders.OrderCtoBuilder;
import io.oasp.gastronomy.restaurant.common.builders.OrderEtoBuilder;
import io.oasp.gastronomy.restaurant.common.builders.OrderPositionEtoBuilder;
import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.MealEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
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

// TODO evtl. in Testüberklasse injizieren
// TODO mit Jörg reden ob statisch oder injiziert
// TODO macht das Sinn die sample Attribute hier zu definieren
public class SampleToCreator {

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

  // TODO check if salesmanagement or tablemanagement
  protected static final long SAMPLE_TABLE_ID = 101;

  // salesmanagement
  public static final long SAMPLE_ORDER_ID = 1;

  public static final long SAMPLE_ORDERPOSITION_ID = 1;

  public static final String SAMPLE_ORDER_COMMENT = "mit Ketchup";

  public static final long SAMPLE_BILL_ID = 1;

  // offermanagement
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

    OrderPositionEto sampleOrderPositionEto = new OrderPositionEtoBuilder().orderId(orderId).offerId(SAMPLE_OFFER_ID)
        .offerName(SAMPLE_OFFER_NAME).price(SAMPLE_OFFER_PRICE).comment(SAMPLE_ORDER_COMMENT).createNew();
    return sampleOrderPositionEto;
  }

  public static final BillEto createSampleBillEto() {

    ArrayList sampeOrderPositionIdList = new ArrayList<Long>();
    sampeOrderPositionIdList.add(SAMPLE_ORDERPOSITION_ID);
    BillEto sampleBillEto = new BillEtoBuilder().orderPositionIds(sampeOrderPositionIdList).createNew();
    return sampleBillEto;
  }

}
