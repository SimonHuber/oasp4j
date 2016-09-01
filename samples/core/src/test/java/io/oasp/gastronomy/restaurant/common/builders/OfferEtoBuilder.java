package io.oasp.gastronomy.restaurant.common.builders;

import java.util.LinkedList;
import java.util.List;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.offermanagement.common.api.datatype.OfferState;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;

public class OfferEtoBuilder {

  public static final String NAME_OFFER_SCHNITZEL = "Schnitzel-Menü";

  private List<P<OfferEto>> parameterToBeApplied;

  public OfferEtoBuilder() {

    this.parameterToBeApplied = new LinkedList<P<OfferEto>>();
    fillMandatoryFields();
    fillMandatoryFields_custom();
  }

  /**
   * Fills all mandatory fields by default. (will be overwritten on re-generation)
   */
  private void fillMandatoryFields() {

  }

  public OfferEtoBuilder number(final Long number) {

    this.parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setNumber(number);
      }
    });
    return this;
  }

  public OfferEtoBuilder mealId(final Long mealId) {

    this.parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setMealId(mealId);
      }
    });
    return this;
  }

  public OfferEtoBuilder drinkId(final Long drinkId) {

    this.parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setDrinkId(drinkId);
      }
    });
    return this;
  }

  public OfferEtoBuilder sideDishId(final Long sideDishId) {

    this.parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setSideDishId(sideDishId);
      }
    });
    return this;
  }

  public OfferEtoBuilder state(final OfferState state) {

    this.parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setState(state);
      }
    });
    return this;
  }

  public OfferEtoBuilder name(final String name) {

    this.parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setName(name);
      }
    });
    return this;
  }

  public OfferEtoBuilder description(final String description) {

    this.parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setDescription(description);
      }
    });
    return this;
  }

  public OfferEtoBuilder revision(final Number revision) {

    this.parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setRevision(revision);
      }
    });
    return this;
  }

  public OfferEtoBuilder price(final Money price) {

    this.parameterToBeApplied.add(new P<OfferEto>() {
      @Override
      public void apply(OfferEto target) {

        target.setPrice(price);
      }
    });
    return this;
  }

  public OfferEto createNew() {

    OfferEto offereto = new OfferEto();
    for (P<OfferEto> parameter : this.parameterToBeApplied) {
      parameter.apply(offereto);
    }
    return offereto;
  }

  /**
   * Might be enrichted to users needs (will not be overwritten)
   */
  private void fillMandatoryFields_custom() {

  }

}
