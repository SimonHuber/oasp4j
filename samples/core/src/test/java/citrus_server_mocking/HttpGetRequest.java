package citrus_server_mocking;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.general.common.base.AbstractRestServiceTest;
import io.oasp.gastronomy.restaurant.salesmanagement.service.impl.rest.SalesmanagementRestTestConfiguration;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringBootApp.class, SalesmanagementRestTestConfiguration.class })
@TestPropertySource(properties = { "flyway.locations=filesystem:src/test/resources/db/tablemanagement" })
public class HttpGetRequest extends AbstractRestServiceTest {

  /**
   * @param args
   * @throws IOException
   */

  @Test
  public void httpGetRequestActionTest() {

    RestTemplate template = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", "text/plain,application/json,application/*+json,*/*");
    headers.add("Connection", "keep-alive");
    headers.add("Content-Type", "text/plain;charset=ISO-8859-1");
    HttpEntity<String> getRequest = new HttpEntity<>(headers);
    ResponseEntity<String> getResponse =
        template.exchange("http://localhost:8081/test", HttpMethod.GET, getRequest, String.class);
    assertThat(getResponse).isNotNull();

    byte[] encodedFileContent = null;
    try {
      encodedFileContent = Files.readAllBytes(Paths.get("src/test/resources/orderPositionPayload.json"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String fileContent = new String(encodedFileContent, Charset.defaultCharset());
    assertThat(getResponse.getBody()).isEqualTo(fileContent);

  }
}
