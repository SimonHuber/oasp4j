package io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest;

import static io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest.SalesmanagementRestServiceTestHelper.INITIAL_NUMBER_OF_ORDERS;
import static io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest.SalesmanagementRestServiceTestHelper.INITIAL_NUMBER_OF_ORDER_POSITIONS;
import static io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest.SalesmanagementRestServiceTestHelper.SAMPLE_DRINK_STATE;
import static io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest.SalesmanagementRestServiceTestHelper.SAMPLE_OFFER_ID;
import static io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest.SalesmanagementRestServiceTestHelper.SAMPLE_OFFER_NAME;
import static io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest.SalesmanagementRestServiceTestHelper.SAMPLE_ORDER_POSITION_STATE;
import static io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest.SalesmanagementRestServiceTestHelper.SAMPLE_ORDER_STATE;
import static io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest.SalesmanagementRestServiceTestHelper.SAMPLE_PRICE;
import static io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest.SalesmanagementRestServiceTestHelper.SAMPLE_TABLE_ID;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.salesmanagement.common.api.datatype.OrderPositionState;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderCto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderPositionEto;
import io.oasp.gastronomy.restaurant.salesmanagement.logic.api.to.OrderSearchCriteriaTo;
import io.oasp.module.basic.configuration.OaspProfile;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;
import io.oasp.module.jpa.common.api.to.PaginationTo;
import io.oasp.module.test.common.base.SubsystemTest;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */

// loads beans defined through SpringBootApp.class
@SpringApplicationConfiguration(classes = { SpringBootApp.class, SalesmanagementRestTestConfiguration.class })
// context loader knows that you want to test a web application
// specify random port
@WebIntegrationTest("server.port:0")
// activate a profile, to use beans defined in profile @Profile(OaspProfile.JUNIT_TEST)
// in file TestWebSecurity
@ActiveProfiles(profiles = { OaspProfile.JUNIT_TEST })

// runs also without the following
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)

public class SalesmanagementRestServiceTest extends SubsystemTest {

  @Value("${local.server.port}")
  private int port;

  @Inject
  private SalesmanagementRestServiceTestHelper helper;

  public SalesmanagementRestServiceTest() {
    super();
  }

  @PostConstruct
  public void beforeTest() {

    this.helper.init(this.port);

  }

  @Test
  public void findOrder() {

    // given
    long sampleOrderId = INITIAL_NUMBER_OF_ORDERS + 1;
    OrderCto sampleOrderCto = this.helper.createSampleOrderCto(SAMPLE_TABLE_ID);
    this.helper.getService().saveOrder(sampleOrderCto);

    // when
    OrderEto expectedOrderEto = this.helper.getService().findOrder(sampleOrderId);

    // then
    assertThat(expectedOrderEto).isNotNull();
    assertThat(expectedOrderEto.getId()).isEqualTo(sampleOrderId);
    assertThat(expectedOrderEto.getTableId()).isEqualTo(SAMPLE_TABLE_ID);
  }

  @Test
  public void findOrders() {

    // MultivaluedMap<String, String> myParameterMap = new MultivaluedHashMap<>();
    // myParameterMap.add("tableId", "102");
    // myParameterMap.add("state", OrderState.OPEN.toString());
    // myParameterMap.add("pagination[page]", "1");
    // UriInfo uriInfo = Mockito.mock(UriInfo.class);
    // Mockito.when(uriInfo.getQueryParameters()).thenReturn(myParameterMap);
    //
    //

    PaginatedListTo<OrderCto> orders = this.helper.getService().findOrders(null);
    assertThat(orders).isNotNull();

    // System.out.println("\n\n\n---------------OHWACHT!!!!----------------\n\n\n");
    // for (OrderCto cto : orders.getResult()) {

    // LOG.debug("cto: " + cto.getOrder().getTableId());
    // System.out.println(("cto: " + cto.getOrder().getTableId()));
    // }

    assertThat(orders).isNotNull();

  }

  @Test
  public void findOrdersByPost() {

    // given
    long sampleOrderId = INITIAL_NUMBER_OF_ORDERS + 1;
    long tableId = SAMPLE_TABLE_ID + 1;

    OrderSearchCriteriaTo criteria = new OrderSearchCriteriaTo();
    criteria.setTableId(tableId);
    criteria.setState(SAMPLE_ORDER_STATE);
    PaginationTo pagination = PaginationTo.NO_PAGINATION;
    criteria.setPagination(pagination);

    OrderCto sampleOrderCto = this.helper.createSampleOrderCto(tableId);
    this.helper.getService().saveOrder(sampleOrderCto);

    // when
    PaginatedListTo<OrderCto> orderCtoList = this.helper.getService().findOrdersByPost(criteria);

    // then
    // TODO Jonas, assert obsolete?
    assertThat(orderCtoList).isNotNull();
    // TODO Jonas
    assertThat(orderCtoList.getResult().size()).isEqualTo(1);
    assertThat(orderCtoList.getResult().get(0).getOrder().getId()).isEqualTo(sampleOrderId);
    assertThat(orderCtoList.getResult().get(0).getOrder().getTableId()).isEqualTo(tableId);
    assertThat(orderCtoList.getResult().get(0).getOrder().getState()).isEqualTo(SAMPLE_ORDER_STATE);

  }

  @Test
  public void cancelOrderPosition() {

    // given
    long sampleOrderId = INITIAL_NUMBER_OF_ORDERS + 1;

    OrderCto sampleOrderCto = this.helper.createSampleOrderCto(SAMPLE_TABLE_ID + 1);
    OrderCto responseOrderCto = this.helper.getService().saveOrder(sampleOrderCto);
    long responseOrderId = responseOrderCto.getOrder().getId();

    OrderPositionEto sampleOrderPositionEto = this.helper.createSampleOrderPositionEto(sampleOrderId);
    OrderPositionEto responseOrderPositionEto = this.helper.getService().saveOrderPosition(sampleOrderPositionEto);
    long sampleOrderPositionId = responseOrderPositionEto.getId();

    OrderEto sampleOrderEto = new OrderEto();
    sampleOrderEto.setId(responseOrderId);
    sampleOrderCto.setOrder(sampleOrderEto);

    // when
    this.helper.getService().saveOrder(sampleOrderCto);

    // TODO Jonas, is this also execution
    responseOrderPositionEto = this.helper.getService().findOrderPosition(sampleOrderPositionId);

    // then
    assertThat(responseOrderPositionEto.getState()).isEqualTo(OrderPositionState.CANCELLED);

    // TODO Jonas where is not scope of the test
    assertThat(responseOrderPositionEto.getId()).isEqualTo(sampleOrderPositionId);
    assertThat(responseOrderId).isEqualTo(sampleOrderId);

  }

  @Test
  public void findOrderPositions() {

    // given
    OrderPositionEto sampleOrderPositionEto = this.helper.createSampleOrderPositionEto(INITIAL_NUMBER_OF_ORDERS);
    this.helper.getService().saveOrderPosition(sampleOrderPositionEto);

    // when
    List<OrderPositionEto> orderPositionEtos = this.helper.getService().findOrderPositions(null);

    // then
    assertThat(this.helper.getNumberOfOrderPositions()).isEqualTo(INITIAL_NUMBER_OF_ORDER_POSITIONS + 1);
    for (int i = 0; i < orderPositionEtos.size(); i++) {
      assertThat(orderPositionEtos.get(i).getId()).isEqualTo(i + 1);
      assertThat(orderPositionEtos.get(i).getOrderId()).isEqualTo(INITIAL_NUMBER_OF_ORDERS);
    }
  }

  @Test
  public void findOrderPosition() {

    // given
    OrderPositionEto sampleOrderPositionEto = this.helper.createSampleOrderPositionEto(INITIAL_NUMBER_OF_ORDERS);
    this.helper.getService().saveOrderPosition(sampleOrderPositionEto);

    // when
    OrderPositionEto expectedOrderPositionEto =
        this.helper.getService().findOrderPosition(this.helper.getNumberOfOrderPositions());

    // then
    assertThat(expectedOrderPositionEto).isNotNull();
    assertThat(this.helper.getNumberOfOrderPositions()).isEqualTo(INITIAL_NUMBER_OF_ORDER_POSITIONS + 1);
    assertThat(expectedOrderPositionEto.getId()).isEqualTo(this.helper.getNumberOfOrderPositions());
    assertThat(expectedOrderPositionEto.getOrderId()).isEqualTo(INITIAL_NUMBER_OF_ORDERS);
    assertThat(expectedOrderPositionEto.getOfferId()).isEqualTo(SAMPLE_OFFER_ID);
    assertThat(expectedOrderPositionEto.getOfferName()).isEqualTo(SAMPLE_OFFER_NAME);
    assertThat(expectedOrderPositionEto.getState()).isEqualTo(SAMPLE_ORDER_POSITION_STATE);
    assertThat(expectedOrderPositionEto.getDrinkState()).isEqualTo(SAMPLE_DRINK_STATE);
    assertThat(expectedOrderPositionEto.getPrice()).isEqualTo(SAMPLE_PRICE);
  }
}
/*
 * public OrderPositionEto findOrderPosition(long orderPositionId) {
 *
 * return salesmanagement.findOrderPosition(orderPositionId);
 *
 * }
 */
