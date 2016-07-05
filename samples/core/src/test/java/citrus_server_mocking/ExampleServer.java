package citrus_server_mocking;

import static citrus_server_mocking.ServerMockHelper.GET_ALL_CUSTOMER_DATA;
import static citrus_server_mocking.ServerMockHelper.GET_ORDER_POSITION;
import static citrus_server_mocking.ServerMockHelper.getJSONFromFile;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.design.TestDesigner;
import com.consol.citrus.dsl.junit.JUnit4CitrusTest;

/**
 * This test demands a list of offers from an external server which is mocked with WireMock. The HTTP request and
 * response is stubbed to simulate the use case. For the received list of offers a batch job is initialized to save or
 * update the new data.
 *
 * @author sroeger
 */

// @SpringApplicationConfiguration(classes = { SpringBootApp.class, SalesmanagementRestTestConfiguration.class })
// @TestPropertySource(properties = { "flyway.locations=filesystem:src/test/resources/db/tablemanagement" })
public class ExampleServer extends JUnit4CitrusTest {

  // @Test
  // @CitrusTest
  // public void purge(@CitrusResource TestDesigner designer) {
  //
  // designer.purgeEndpoints().endpointNames("helloHttpServer");
  // }

  @Test
  @CitrusTest
  public void deliverOrderPosition(@CitrusResource TestDesigner designer) {

    designer.http().server("helloHttpServer").get(GET_ORDER_POSITION).accept("application/json").timeout(600000000);
    String fileContent = getJSONFromFile("src/test/resources/customer.json");
    designer.http().server("helloHttpServer").respond(HttpStatus.OK).payload(fileContent).version("HTTP/1.1")
        .contentType("application/json");

  }

  @Test
  @CitrusTest
  public void deliverAllCustomerDates(@CitrusResource TestDesigner designer) {

    designer.http().server("helloHttpServer").get(GET_ALL_CUSTOMER_DATA).accept("application/json").timeout(600000000);
    String fileContent = getJSONFromFile("src/test/resources/customer.json");
    designer.http().server("helloHttpServer").respond(HttpStatus.OK).payload(fileContent).version("HTTP/1.1")
        .contentType("application/json");
  }
}
