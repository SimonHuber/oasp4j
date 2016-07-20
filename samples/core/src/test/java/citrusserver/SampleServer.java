package citrusserver;

import static citrusserver.ServerMockHelper.GET_ALL_CUSTOMER_DATA;
import static citrusserver.ServerMockHelper.GET_CUSTOMER_ADDRESS;
import static citrusserver.ServerMockHelper.GET_ORDER_POSITION;
import static citrusserver.ServerMockHelper.GET_ORDER_POSITIONS;
import static citrusserver.ServerMockHelper.RESOURCE_PATH;
import static citrusserver.ServerMockHelper.getJSONFromFile;

import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
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
 * @author shuber
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SampleServer extends JUnit4CitrusTest {

  protected static long TIME_OUT = 600000000;

  @BeforeTest
  public void purge(@CitrusResource TestDesigner designer) {

    designer.purgeEndpoints().endpointNames("sampleHttpServer");
  }

  @Test
  @CitrusTest
  public void deliverOrderPosition(@CitrusResource TestDesigner designer) {

    designer.http().server("sampleHttpServer").get(GET_ORDER_POSITION).accept("application/json").timeout(TIME_OUT);
    String fileContent = getJSONFromFile("src/test/resources/orderPositionPayload.json");
    designer.http().server("sampleHttpServer").respond(HttpStatus.OK).payload(fileContent).version("HTTP/1.1")
        .contentType("application/json");

  }

  @Test
  @CitrusTest
  public void deliverOrderPositions(@CitrusResource TestDesigner designer) {

    designer.http().server("sampleHttpServer").get(GET_ORDER_POSITIONS).accept("application/json").timeout(TIME_OUT);
    String fileContent = getJSONFromFile("src/test/resources/orderPositionsPayload.json");
    designer.http().server("sampleHttpServer").respond(HttpStatus.OK).payload(fileContent).version("HTTP/1.1")
        .contentType("application/json");

  }

  @Test
  @CitrusTest
  public void deliverAllCustomerDates(@CitrusResource TestDesigner designer) {

    designer.http().server("sampleHttpServer").get(GET_ALL_CUSTOMER_DATA).accept("application/json").timeout(TIME_OUT);
    String fileContent = getJSONFromFile(RESOURCE_PATH + "customer.json");
    designer.http().server("sampleHttpServer").respond(HttpStatus.OK).payload(fileContent).version("HTTP/1.1")
        .contentType("application/json");
  }

  @Test
  @CitrusTest
  public void deliverCustomerAddress(@CitrusResource TestDesigner designer) {

    designer.http().server("sampleHttpServer").get(GET_CUSTOMER_ADDRESS).accept("application/json").timeout(TIME_OUT);
    String fileContent = getJSONFromFile(RESOURCE_PATH + "customer.json");
    JSONObject jsonObject = new JSONObject(fileContent);
    JSONObject address = jsonObject.getJSONObject("address");
    designer.http().server("sampleHttpServer").respond(HttpStatus.OK).payload(address.toString()).version("HTTP/1.1")
        .contentType("application/json");
  }

}
