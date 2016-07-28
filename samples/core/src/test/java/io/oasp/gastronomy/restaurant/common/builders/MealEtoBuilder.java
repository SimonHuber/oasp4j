package io.oasp.gastronomy.restaurant.common.builders;

import java.util.LinkedList;
import java.util.List;

import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.MealEto;

public class MealEtoBuilder {

  private List<P<MealEto>> parameterToBeApplied;

  public MealEtoBuilder() {

    parameterToBeApplied = new LinkedList<P<MealEto>>();
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

  public MealEtoBuilder pictureId(final Long pictureId) {

    parameterToBeApplied.add(new P<MealEto>() {
      @Override
      public void apply(MealEto target) {

        target.setPictureId(pictureId);
      }
    });
    return this;
  }

  public MealEtoBuilder name(final String name) {

    parameterToBeApplied.add(new P<MealEto>() {
      @Override
      public void apply(MealEto target) {

        target.setName(name);
      }
    });
    return this;
  }

  public MealEtoBuilder description(final String description) {

    parameterToBeApplied.add(new P<MealEto>() {
      @Override
      public void apply(MealEto target) {

        target.setDescription(description);
      }
    });
    return this;
  }

  public MealEtoBuilder revision(final Number revision) {

    parameterToBeApplied.add(new P<MealEto>() {
      @Override
      public void apply(MealEto target) {

        target.setRevision(revision);
      }
    });
    return this;
  }

  public MealEto createNew() {

    MealEto mealeto = new MealEto();
    for (P<MealEto> parameter : parameterToBeApplied) {
      parameter.apply(mealeto);
    }
    return mealeto;
  }

}
