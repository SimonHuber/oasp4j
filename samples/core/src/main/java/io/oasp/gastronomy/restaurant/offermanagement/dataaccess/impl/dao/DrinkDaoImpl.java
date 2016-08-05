package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.impl.dao;

import javax.inject.Named;

import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.DrinkEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.DrinkDao;

/**
 * Implementation of {@link DrinkDao}.
 *
 * @author hohwille
 */
@Named
public class DrinkDaoImpl extends ApplicationMasterDataDaoImpl<DrinkEntity> implements DrinkDao {

  /**
   * The constructor.
   */
  public DrinkDaoImpl() {

    super();
  }

  @Override
  protected Class<DrinkEntity> getEntityClass() {

    return DrinkEntity.class;
  }

}
