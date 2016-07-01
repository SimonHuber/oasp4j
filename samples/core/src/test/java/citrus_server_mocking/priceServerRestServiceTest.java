package citrus_server_mocking;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.design.TestDesigner;
import com.consol.citrus.dsl.junit.JUnit4CitrusTest;
import com.consol.citrus.message.MessageType;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest.SalesmanagementRestTestConfiguration;

/**
 * This test demands a list of offers from an external server which is mocked with WireMock. The HTTP request and
 * response is stubbed to simulate the use case. For the received list of offers a batch job is initialized to save or
 * update the new data.
 *
 * @author sroeger
 */

@SpringApplicationConfiguration(classes = { SpringBootApp.class, SalesmanagementRestTestConfiguration.class })
@TestPropertySource(properties = { "flyway.locations=filesystem:src/test/resources/db/tablemanagement" })
// @SpringApplicationConfiguration(classes = RestaurantTestConfig.class)
public class priceServerRestServiceTest extends JUnit4CitrusTest {

  // @Test
  // @CitrusTest
  // public void httpServerActionTest() {
  //
  // send("salesmanagementClient").http().method(HttpMethod.GET).header("Authorization", getAuthentificatedHeaders())
  // .endpoint("http://localhost:" + this.port + "/services/rest/salesmanagement/v1/orderposition/1");
  // receive("salesmanagementClient")
  // .endpoint("http://localhost:" + this.port + "/services/rest/salesmanagement/v1/orderposition/1")
  // .messageType(MessageType.JSON).http().status(HttpStatus.OK).validate("$.id", 1).validate("$.orderId", 1);
  // }

  @Inject
  final RestTemplate template = null;

  //
  // @Test
  // @CitrusTest
  // public void httpServerActionTestRunner() {
  //
  // echo("\n\00000000000000000\n\n");
  //
  // echo("\n\n111111111111111\n\n");
  //
  // http().server("helloHttpServer").get("/test").timeout(600000000);
  // echo("\n\n222222222222222222\n\n");
  // HttpHeaders headers = new HttpHeaders();
  // headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
  // headers.add("Connection", "keep-alive");
  // HttpEntity<String> getRequest = new HttpEntity<>(headers);
  // ResponseEntity<String> getResponse =
  // this.template.exchange("http://localhost:8081/test", HttpMethod.GET, getRequest, String.class);
  //
  // echo("\n\n\n" + getResponse + "\n\n\n");
  // echo("\n\n\n" + getResponse.getBody() + "\n\n\n");
  // echo("\n\n\n" + getResponse.getHeaders() + "\n\n\n");
  //
  // echo("\n\33333333333333333333\n\n");
  // http().server("helloHttpServer").respond(HttpStatus.OK).messageType(MessageType.JSON)
  // .payload(new ClassPathResource("orderPositionPayload.json"));
  //
  // }

  @Test
  @CitrusTest
  public void purge(@CitrusResource TestDesigner designer) {

    designer.echo("\n\nBefore\n\n");
    designer.purgeEndpoints().endpointNames("helloHttpServer");

  }

  @Test
  @CitrusTest
  public void httpServerActionTestDesigner(@CitrusResource final TestDesigner designer) {

    Thread t = new Thread(new Runnable() {

      @Override
      public void run() {

        designer.echo("\n\n111111111111111\n\n");
        designer.http().server("helloHttpServer").get("/test").timeout(600000000);
        // TODO Auto-generated method stub
        designer.echo("\n\33333333333333333333\n\n");
        designer.http().server("helloHttpServer").respond(HttpStatus.OK).messageType(MessageType.JSON)
            .payload(new ClassPathResource("orderPositionPayload.json")).version("HTTP/1.1");
        // .payload("{\"id\":1}").version("HTTP/1.1");
      }
    });
    t.start();

    // Thread t2 = new Thread(new Runnable() {
    //
    // @Override
    // public void run() {
    //
    // designer.echo("\n\n222222222222222222\n\n");
    // HttpHeaders headers = new HttpHeaders();
    // // headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    // headers.add("Connection", "keep-alive");
    // HttpEntity<String> getRequest = new HttpEntity<>(headers);
    // ResponseEntity<String> getResponse = priceServerRestServiceTest.this.template
    // .exchange("http://localhost:8081/test", HttpMethod.GET, getRequest, String.class);
    //
    // designer.echo("\n\n\n" + getResponse + "\n\n\n");
    // designer.echo("\n\n\n" + getResponse.getBody() + "\n\n\n");
    // designer.echo("\n\n\n" + getResponse.getHeaders() + "\n\n\n");
    // }
    // });
    // t2.start();

    designer.echo("\n\n222222222222222222\n\n");
    HttpHeaders headers = new HttpHeaders();
    // headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    headers.add("Connection", "keep-alive");
    HttpEntity<String> getRequest = new HttpEntity<>(headers);
    ResponseEntity<String> getResponse = priceServerRestServiceTest.this.template.exchange("http://localhost:8081/test",
        HttpMethod.GET, getRequest, String.class);

    designer.echo("\n\n\n" + getResponse + "\n\n\n");
    designer.echo("\n\n\n" + getResponse.getBody() + "\n\n\n");
    designer.echo("\n\n\n" + getResponse.getHeaders() + "\n\n\n");

  }
}