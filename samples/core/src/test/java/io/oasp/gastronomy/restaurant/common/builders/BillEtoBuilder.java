package io.oasp.gastronomy.restaurant.common.builders;

import java.util.LinkedList;
import java.util.List;

import io.oasp.gastronomy.restaurant.general.common.api.datatype.Money;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.BillEto;

public class BillEtoBuilder {

  private List<P<BillEto>> parameterToBeApplied;

  public BillEtoBuilder() {

    parameterToBeApplied = new LinkedList<P<BillEto>>();
    fillMandatoryFields();
    fillMandatoryFields_custom();
  }

  /**
   * Fills all mandatory fields by default. (will be overwritten on re-generation)
   */
  private void fillMandatoryFields() {

  }

  public BillEtoBuilder orderPositionIds(final List orderPositionIds) {

    parameterToBeApplied.add(new P<BillEto>() {
      @Override
      public void apply(BillEto target) {

        target.setOrderPositionIds(orderPositionIds);
      }
    });
    return this;
  }

  public BillEtoBuilder total(final Money total) {

    parameterToBeApplied.add(new P<BillEto>() {
      @Override
      public void apply(BillEto target) {

        target.setTotal(total);
      }
    });
    return this;
  }

  public BillEtoBuilder tip(final Money tip) {

    parameterToBeApplied.add(new P<BillEto>() {
      @Override
      public void apply(BillEto target) {

        target.setTip(tip);
      }
    });
    return this;
  }

  public BillEtoBuilder payed(final boolean payed) {

    parameterToBeApplied.add(new P<BillEto>() {
      @Override
      public void apply(BillEto target) {

        target.setPayed(payed);
      }
    });
    return this;
  }

  public BillEtoBuilder revision(final Number revision) {

    parameterToBeApplied.add(new P<BillEto>() {
      @Override
      public void apply(BillEto target) {

        target.setRevision(revision);
      }
    });
    return this;
  }

  public BillEto createNew() {

    BillEto billeto = new BillEto();
    for (P<BillEto> parameter : parameterToBeApplied) {
      parameter.apply(billeto);
    }
    return billeto;
  }

  /**
   * Might be enrichted to users needs (will not be overwritten)
   */
  private void fillMandatoryFields_custom() {

  }

}
