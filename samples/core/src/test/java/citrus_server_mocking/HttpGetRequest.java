package citrus_server_mocking;

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
public class HttpGetRequest {

  // private RestTemplate template;

  /**
   * @param args
   */

  @Test
  public void httpGetRequestAction() {

    RestTemplate template = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    headers.add("Connection", "keep-alive");
    HttpEntity<String> getRequest = new HttpEntity<>(headers);
    ResponseEntity<String> getResponse =
        template.exchange("http://localhost:8081/test", HttpMethod.GET, getRequest, String.class);
  }
}
