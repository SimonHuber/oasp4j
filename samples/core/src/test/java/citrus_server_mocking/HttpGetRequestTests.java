package citrus_server_mocking;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.inject.Inject;

import org.apache.commons.codec.binary.Base64;
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
public class HttpGetRequestTests extends AbstractRestServiceTest {

  /**
   * @param args
   * @throws IOException
   */

  @Inject
  private RestTemplate template;

  @Test
  public void getOrderPosition() {

    HttpHeaders headers = new HttpHeaders();// = getAuthentificatedHeaders();
    headers.add(HttpHeaders.ACCEPT, "application/json");
    headers.add(HttpHeaders.CONNECTION, "keep-alive");
    HttpEntity<String> getRequest = new HttpEntity<>(headers);
    ResponseEntity<String> getResponse =
        this.template.exchange("http://localhost:8081/getOrderPosition", HttpMethod.GET, getRequest, String.class);
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

  @Test
  public void getAllCustomerDates() {

    HttpHeaders headers = new HttpHeaders();// = getAuthentificatedHeaders();
    headers.add(HttpHeaders.ACCEPT, "application/json");
    headers.add(HttpHeaders.CONNECTION, "keep-alive");
    HttpEntity<String> getRequest = new HttpEntity<>(headers);
    ResponseEntity<String> getResponse =
        this.template.exchange("http://localhost:8081/getAllCustomerDates", HttpMethod.GET, getRequest, String.class);
    assertThat(getResponse).isNotNull();

    byte[] encodedFileContent = null;
    try {
      encodedFileContent = Files.readAllBytes(Paths.get("src/test/resources/customer.json"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String fileContent = new String(encodedFileContent, Charset.defaultCharset());
    assertThat(getResponse.getBody()).isEqualTo(fileContent);
  }

  private HttpHeaders getAuthentificatedHeaders() {

    String plainCreds = "waiter" + ":" + "waiter";
    byte[] plainCredsBytes = plainCreds.getBytes();
    byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
    String base64Creds = new String(base64CredsBytes);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Basic " + base64Creds);

    return headers;
  }
}
