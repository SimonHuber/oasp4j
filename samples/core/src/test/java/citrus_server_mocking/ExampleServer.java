package citrus_server_mocking;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

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

  @Test
  @CitrusTest
  public void purge(@CitrusResource TestDesigner designer) {

    designer.echo("\n\nBefore\n\n");
    designer.purgeEndpoints().endpointNames("helloHttpServer");

  }

  @Test
  @CitrusTest
  public void httpServerActionTest(@CitrusResource TestDesigner designer) {

    designer.http().server("helloHttpServer").get("/test").contentType("text/plain;charset=ISO-8859-1")
        .accept("text/plain,application/json,application/*+json,*/*").timeout(600000000)

    ;
    byte[] encodedFileContent = null;
    try {
      encodedFileContent = Files.readAllBytes(Paths.get("src/test/resources/orderPositionPayload.json"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String fileContent = new String(encodedFileContent, Charset.defaultCharset());
    designer.http().server("helloHttpServer").respond(HttpStatus.OK)
        // .payload(new ClassPathResource("orderPositionPayload.json")).version("HTTP/1.1")
        .payload(fileContent);

    // .contentType("text/plain;charset=ISO-8859-1")// .header("CustomHeaderId", "${custom_header_id}")
    // .header("X-MessageId", "${message_id}");
    ;

    //
    // assertThat(postResponse.getBody()).isNotNull();

  }
}
