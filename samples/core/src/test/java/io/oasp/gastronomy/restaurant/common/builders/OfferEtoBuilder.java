package io.oasp.gastronomy.restaurant.common.builders;

import java.util.LinkedList;
import java.util.List;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.datatype.OfferState;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;

public class OfferEtoBuilder {

  private List<P<OfferEto>> parameterToBeApplied;

  public OfferEtoBuilder() {

    parameterToBeApplied = new LinkedList<P<OfferEto>>();
    fillMandatoryFields();
    fillMandatoryFields_custom();
  }

  /**
   * Might be enrichted to users needs (will not be overwritten)
   */
  private void fillMandatoryFields_custom() {

  }

  /**
   * Fills all mandatory fields by default. (will be overwritten on re-generation)
   */
  private void fillMandatoryFields() {

  }

  public OfferEtoBuilder number(final Long number) {

    parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setNumber(number);
      }
    });
    return this;
  }

  public OfferEtoBuilder mealId(final Long mealId) {

    parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setMealId(mealId);
      }
    });
    return this;
  }

  public OfferEtoBuilder drinkId(final Long drinkId) {

    parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setDrinkId(drinkId);
      }
    });
    return this;
  }

  public OfferEtoBuilder sideDishId(final Long sideDishId) {

    parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setSideDishId(sideDishId);
      }
    });
    return this;
  }

  public OfferEtoBuilder state(final OfferState state) {

    parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setState(state);
      }
    });
    return this;
  }

  public OfferEtoBuilder name(final String name) {

    parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setName(name);
      }
    });
    return this;
  }

  public OfferEtoBuilder description(final String description) {

    parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setDescription(description);
      }
    });
    return this;
  }

  public OfferEtoBuilder revision(final Number revision) {

    parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setRevision(revision);
      }
    });
    return this;
  }

  public OfferEtoBuilder price(final Money price) {

    parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setPrice(price);
      }
    });
    return this;
  }

  public OfferEto createNew() {

    OfferEto offereto = new OfferEto();
    for (P<OfferEto> parameter : parameterToBeApplied) {
      parameter.apply(offereto);
    }
    return offereto;
  }

}
