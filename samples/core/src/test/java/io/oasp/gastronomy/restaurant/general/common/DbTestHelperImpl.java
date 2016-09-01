package io.oasp.gastronomy.restaurant.general.common;

import org.flywaydb.core.Flyway;

import io.oasp.module.test.common.helper.api.DbTestHelper;

/**
 * This class provides methods for handling the database during testing where resets (and other operations) may be
 * necessary.
 *
 * @author jmolinar, shuber
 */
public class DbTestHelperImpl implements DbTestHelper {
  private Flyway flyway;

  /**
   * The constructor.
   *
   * @param flyway an instance of type {@link Flyway}.
   */
  public DbTestHelperImpl(Flyway flyway) {

    this.flyway = flyway;
  }

  @Override
  public void dropDatabase() {

    this.flyway.clean();
  }

  /**
   * Calls {@link #dropDatabase()} internally, and migrates the database.
   */
  @Override
  public void resetDatabase() {

    dropDatabase();

    this.flyway.migrate();
  }
}
