package io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.design.TestDesigner;
import com.consol.citrus.dsl.junit.JUnit4CitrusTest;
import com.consol.citrus.message.MessageType;

import io.oasp.gastronomy.restaurant.SpringBootApp;

/**
 * This class serves as an example of how to perform a subsystem test (e.g., call a *RestService interface). The test
 * database is accessed via HTTP requests to the server running the restaurant application.
 *
 * @author shuber
 */
@WebIntegrationTest
@SpringApplicationConfiguration(classes = { SpringBootApp.class, SalesmanagementRestTestConfiguration.class })
@TestPropertySource(properties = { "flyway.locations=filesystem:src/test/resources/db/tablemanagement" })

public class SalesmanagementHttpRestServiceTestCitrus extends JUnit4CitrusTest {

  /**
   * The port of the web server during the test.
   */
  @Value("${local.server.port}")
  protected int port;

  @Test
  @CitrusTest
  public void getOrderPosition(@CitrusResource TestDesigner designer) {

    designer.send("salesmanagementClient").http().method(HttpMethod.GET)
        .header("Authorization", getAuthentificatedHeaders()).endpoint(generateBaseUrl() + "orderposition/1");
    designer.receive("salesmanagementClient").endpoint(generateBaseUrl() + "orderposition/1")
        .messageType(MessageType.JSON).http().status(HttpStatus.OK).validate("$.id", 1).validate("$.orderId", 1)
        .ignore("$.offerId");
  }

  @Test
  @CitrusTest
  public void getOrders(@CitrusResource TestDesigner designer) {

    designer.send("salesmanagementClient").http().method(HttpMethod.GET)
        .header("Authorization", getAuthentificatedHeaders()).endpoint(generateBaseUrl() + "order/");
    designer.receive("salesmanagementClient").endpoint(generateBaseUrl() + "order/").messageType(MessageType.JSON)
        .http().status(HttpStatus.OK)

        .validate("$.result[*].order.keySet()", "[modificationCounter, tableId, id, state, revision]")
        .validate("$.result[*].size()", "2").validate("$.result[*].size()", "2");
  }

  /**
   * This method builds an encoded authentication header and returns it
   */
  private String getAuthentificatedHeaders() {

    String plainCreds = "waiter" + ":" + "waiter";
    byte[] plainCredsBytes = plainCreds.getBytes();
    byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
    String base64Creds = new String(base64CredsBytes);

    return "Basic " + base64Creds;
  }

  private String generateBaseUrl() {

    return "http://localhost:" + this.port + "/services/rest/salesmanagement/v1/";
  }

}