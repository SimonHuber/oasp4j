package io.oasp.gastronomy.restaurant.tablemanagement.service.impl.rest;

import java.util.List;

import org.junit.Test;

import io.oasp.gastronomy.restaurant.common.builders.TableEtoBuilder;
import io.oasp.gastronomy.restaurant.general.common.base.RestServiceTest;
import io.oasp.gastronomy.restaurant.tablemanagement.common.api.datatype.TableState;
import io.oasp.gastronomy.restaurant.tablemanagement.logic.api.to.TableEto;
import io.oasp.gastronomy.restaurant.tablemanagement.logic.api.to.TableSearchCriteriaTo;
import io.oasp.gastronomy.restaurant.tablemanagement.service.api.rest.TablemanagementRestService;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * This class serves as an example of how to perform a subsystem test (e.g., call a *RestService interface).
 *
 * @author geazzi, jmolinar, sroeger
 */

// , locations = {"file:src/test/resources/config" })

public class TablemanagementRestServiceTest extends RestServiceTest {

  private TablemanagementRestService service;

  /**
   * Provides initialization previous to the creation of the text fixture.
   */
  @Override
  public void doSetUp() {

    super.doSetUp();
    this.service = getRestTestClientBuilder().build(TablemanagementRestService.class);
  }

  /**
   * Provides clean up after tests.
   */
  @Override
  public void doTearDown() {

    super.doTearDown();
    this.service = null;
  }

  /**
   * This test method serves as an example of how to use {@link RestServiceTest} in practice.
   */
  @Test
  public void testFindTable() {

    // given
    long id = 102;

    // when

    TableEto table = this.service.getTable(id);

    // then
    assertThat(table).isNotNull();
    assertThat(table.getId()).isEqualTo(id);

  }

  /**
   * This test method deletes a table. As a waiter (default login defined in application.properties) does not have the
   * permission to do so, a workaround is needed to login as member "chief". Do not try to delete table 101 as is has an
   * attached order and will fail with error code 400.
   */
  @Test
  public void testDeleteTable() {

    // setup
    getRestTestClientBuilder().setUser("chief");
    getRestTestClientBuilder().setPassword("chief");
    this.service = getRestTestClientBuilder().build(TablemanagementRestService.class);

    // given
    int deleteTableNumber = 102;
    assertThat(this.service.getTable(deleteTableNumber)).isNotNull();

    // when
    this.service.deleteTable(deleteTableNumber);

    // then
    assertThat(this.service.getTable(deleteTableNumber)).isNull();

  }

  /**
   * This test method creates a table using {@link TableEtoBuilder} and saves it into the database. As a waiter (default
   * login defined in application.properties) does not have the permission to do so, a workaround is needed to login as
   * member "chief".
   */
  @Test
  public void testSaveTable() {

    // given
    long tableNumber = 7L;
    long waiterId = 2L;
    getRestTestClientBuilder().setUser("chief");
    getRestTestClientBuilder().setPassword("chief");
    this.service = getRestTestClientBuilder().build(TablemanagementRestService.class);
    TableEto table = new TableEtoBuilder().number(tableNumber).waiterId(waiterId).createNew();
    assertThat(table.getId()).isNull();

    // when
    TableEto savedTable = this.service.saveTable(table);

    // then
    assertThat(savedTable).isNotNull();
    assertThat(savedTable.getId()).isNotNull();
    assertThat(savedTable.getState()).isEqualTo(TableState.FREE);
    assertThat(savedTable.getNumber()).isEqualTo(tableNumber);
    assertThat(savedTable.getWaiterId()).isEqualTo(waiterId);
  }

  /**
   * This test method demonstrates a simple usage of {@link TableSearchCriteriaTo} for searching a table by post with
   * {@link TableState} {@code RESERVED} that is created prior to the search job.
   */
  @Test
  public void testFindTablesByPost() {

    // given
    long tableNumber = 7L;
    long waiterId = 2L;
    TableEto table =
        new TableEtoBuilder().number(tableNumber).waiterId(waiterId).state(TableState.RESERVED).createNew();
    assertThat(table).isNotNull();
    TableEto savedTable = this.service.saveTable(table);
    assertThat(savedTable).isNotNull();
    TableSearchCriteriaTo criteria = new TableSearchCriteriaTo();
    assertThat(criteria).isNotNull();
    criteria.setState(TableState.RESERVED);

    // when
    PaginatedListTo<TableEto> tables = this.service.findTablesByPost(criteria);
    List<TableEto> result = tables.getResult();

    // then
    assertThat(result).isNotEmpty();
    assertThat(result).hasAtLeastOneElementOfType(TableEto.class).extracting("state").contains(TableState.RESERVED);
    assertThat(result).extracting("state").doesNotContain(TableState.FREE);
    assertThat(result).extracting("state").doesNotContain(TableState.OCCUPIED);
    assertThat(result).extracting("waiterId").contains(waiterId);
    assertThat(result).extracting("number").contains(tableNumber);
  }

}
