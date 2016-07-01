package citrus_server_mocking;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
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
public class ExampleServer extends JUnit4CitrusTest {

  @Inject
  final RestTemplate template = null;

  @Test
  @CitrusTest
  public void purge(@CitrusResource TestDesigner designer) {

    designer.echo("\n\nBefore\n\n");
    designer.purgeEndpoints().endpointNames("helloHttpServer");

  }

  @Test
  @CitrusTest
  public void httpServerActionTest(@CitrusResource TestDesigner designer) {

    designer.echo("\n\n111111111111111\n\n");
    designer.http().server("helloHttpServer").post("/test")
        .payload("<testRequestMessage>" + "<text>Hello HttpServer</text>" + "</testRequestMessage>")
        .contentType("text/plain;charset=ISO-8859-1").accept("text/plain,application/json,application/*+json,*/*")
        .timeout(600000000)
        // .header("CustomHeaderId",
        // "${custom_header_id}")
        // .header("Authorization", "Basic c29tZVVzZXJuYW1lOnNvbWVQYXNzd29yZA==")
        // .extractFromHeader("X-MessageId", "message_id");
    ;
    designer.echo("\n\n22222222222222222\n\n");
    designer.http().server("helloHttpServer").respond(HttpStatus.OK)
        .payload("<testResponseMessage>" + "<text>Hello Citrus</text>" + "</testResponseMessage>").version("HTTP/1.1")
        .contentType("text/plain;charset=ISO-8859-1")// .header("CustomHeaderId", "${custom_header_id}")
    // .header("X-MessageId", "${message_id}");
    ;
    designer.echo("\n\n333333333333333333\n\n");
    HttpHeaders headers = new HttpHeaders();
    // headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    headers.add("Connection", "keep-alive");
    HttpEntity<String> postRequest =
        new HttpEntity<>("<testRequestMessage>" + "<text>Hello HttpServer</text>" + "</testRequestMessage>", headers);

    ResponseEntity<String> postResponse =
        this.template.exchange("http://localhost:8081/test", HttpMethod.POST, postRequest, String.class);

    designer.echo("\n\n\n" + postResponse + "\n\n\n");
    designer.echo("\n\n\n" + postResponse.getBody() + "\n\n\n");
    designer.echo("\n\n\n" + postResponse.getHeaders() + "\n\n\n");
    //
    // assertThat(postResponse.getBody()).isNotNull();

  }
}
