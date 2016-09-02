package io.oasp.module.test.common.base;

import org.junit.experimental.categories.Category;

import io.oasp.module.test.common.api.category.CategoryComponentTest;
import io.oasp.module.test.common.helper.api.DbTestHelper;

/**
 * This is the abstract base class for a component test. You are free to create your component tests as you like just by
 * annotating {@link CategoryComponentTest} using {@link Category}. However, in most cases it will be convenient just to
 * extend this class.
 *
 * @see CategoryComponentTest
 *
 * @author hohwille, shuber
 */

public abstract class ComponentDbTest extends ComponentTest {

  protected DbTestHelper dbTestHelper;

  private static boolean DB_NEEDS_RESET = true;

  @Override
  protected void doSetUp() {

    doDatabaseSetUp();
  }

  @Override
  protected void doTearDown() {

    doDatabaseTearDown();
  }

  /**
   * Provides initialization of the database.
   */
  protected void doDatabaseSetUp() {

    if (dbNeedsReset()) {
      getDbTestHelper().resetDatabase(null);
    }
    setDbNeedsReset(true);
  }

  /**
   * Provides clean up of the database.
   */
  protected void doDatabaseTearDown() {

  }

  /**
   * @return {@link DB_NEEDS_RESET}
   */
  protected static boolean dbNeedsReset() {

    return DB_NEEDS_RESET;
  }

  /**
   * sets {@link DB_NEEDS_RESET}.
   */
  protected void setDbNeedsReset(boolean dbNeedsReset) {

    DB_NEEDS_RESET = dbNeedsReset;
  }

  /**
   * @return the {@link DbTestHelper}
   */
  protected DbTestHelper getDbTestHelper() {

    return this.dbTestHelper;
  }
}
