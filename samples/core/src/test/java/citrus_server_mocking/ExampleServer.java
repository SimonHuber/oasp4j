package citrus_server_mocking;

import static citrus_server_mocking.ServerMockHelper.GET_ALL_CUSTOMER_DATA;
import static citrus_server_mocking.ServerMockHelper.GET_CUSTOMER_ADDRESS;
import static citrus_server_mocking.ServerMockHelper.GET_ORDER_POSITION;
import static citrus_server_mocking.ServerMockHelper.getJSONFromFile;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.testng.annotations.BeforeTest;

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

  @BeforeTest
  public void purge(@CitrusResource TestDesigner designer) {

    designer.purgeEndpoints().endpointNames("helloHttpServer");
    designer.purgeEndpoints().endpointNames("helloHttpServer1");
    designer.purgeEndpoints().endpointNames("helloHttpServer2");
    designer.purgeEndpoints().endpointNames("helloHttpServer3");
  }

  @Test
  @CitrusTest
  public void deliverOrderPosition(@CitrusResource TestDesigner designer) {

    designer.echo("\n-----------------------------\ndeliverOrderPosition\nServer1\n");
    designer.http().server("helloHttpServer").get(GET_ORDER_POSITION).accept("application/json").timeout(600000000);
    String fileContent = getJSONFromFile("src/test/resources/orderPositionPayload.json");
    designer.echo("\n-----------------------------\ndeliverOrderPosition\nServer2\n");
    designer.http().server("helloHttpServer").respond(HttpStatus.OK).payload(fileContent).version("HTTP/1.1")
        .contentType("application/json");

  }

  @Test
  @CitrusTest
  public void deliverAllCustomerDates(@CitrusResource TestDesigner designer) {

    designer.echo("\n-----------------------------\ndeliverAllCustomerDates\nServer1\n");
    designer.http().server("helloHttpServer").get(GET_ALL_CUSTOMER_DATA).accept("application/json").timeout(600000000);

    String fileContent = getJSONFromFile("src/test/resources/customer.json");
    designer.echo("\n-----------------------------\ndeliverAllCustomerDates\nServer2\n");
    designer.http().server("helloHttpServer").respond(HttpStatus.OK).payload(fileContent).version("HTTP/1.1")
        .contentType("application/json");
  }

  @Test
  @CitrusTest
  public void deliverCustomerAddress(@CitrusResource TestDesigner designer) {

    designer.echo("\n-----------------------------\ndeliverCustomerAddress\nServer1\n");
    designer.http().server("helloHttpServer").get(GET_CUSTOMER_ADDRESS).accept("application/json").timeout(600000000);
    String fileContent = getJSONFromFile("src/test/resources/customer.json");
    JSONObject jsonObj = new JSONObject(fileContent);
    JSONObject adress = jsonObj.getJSONObject("address");
    designer.echo("\n-----------------------------\ndeliverCustomerAddress\nServer2\n");
    designer.http().server("helloHttpServer").respond(HttpStatus.OK).payload(adress.toString()).version("HTTP/1.1")
        .contentType("application/json");
  }
}
