package io.oasp.module.test.common.helper.api;

/**
 * This interface offers methods to drop ({@link #dropDatabase()}) and reset () a database.
 *
 * @author shuber
 */
public interface DbTestHelper {

  /**
   * Drops the whole database.
   */
  public void dropDatabase();

  /**
   * Migrates the database.
   *
   */
  public void resetDatabase();
}
