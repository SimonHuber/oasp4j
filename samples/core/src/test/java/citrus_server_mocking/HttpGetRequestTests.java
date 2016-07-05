package citrus_server_mocking;

import static citrus_server_mocking.ServerMockHelper.GET_ALL_CUSTOMER_DATA;
import static citrus_server_mocking.ServerMockHelper.GET_CUSTOMER_ADDRESS;
import static citrus_server_mocking.ServerMockHelper.GET_ORDER_POSITION;
import static citrus_server_mocking.ServerMockHelper.getJSONFromFile;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HttpGetRequestTests extends Assertions {

  private RestTemplate template = new RestTemplate();

  /**
   * @param args
   * @throws IOException
   */
  private final int port = 8081;

  private final String SERVER_URL = "http://localhost:";

  @Test
  public void getOrderPosition() {

    HttpEntity<String> getRequest = createGetRequest();
    ResponseEntity<String> getResponse = this.template.exchange(this.SERVER_URL + this.port + GET_ORDER_POSITION,
        HttpMethod.GET, getRequest, String.class);
    assertThat(getResponse).isNotNull();

    String fileContent = getJSONFromFile("src/test/resources/orderPositionPayload.json");
    assertThat(getResponse.getBody()).isEqualTo(fileContent);
  }

  @Test
  public void getAllCustomerDates() {

    HttpEntity<String> getRequest = createGetRequest();
    ResponseEntity<String> getResponse = this.template.exchange(this.SERVER_URL + 8081 + GET_ALL_CUSTOMER_DATA,
        HttpMethod.GET, getRequest, String.class);
    assertThat(getResponse).isNotNull();
    String fileContent = getJSONFromFile("src/test/resources/customer.json");
    assertThat(getResponse.getBody()).isEqualTo(fileContent);
  }

  @Test
  public void getCustomerAddress() {

    HttpEntity<String> getRequest = createGetRequest();
    ResponseEntity<String> getResponse =
        this.template.exchange(this.SERVER_URL + 8081 + GET_CUSTOMER_ADDRESS, HttpMethod.GET, getRequest, String.class);
    assertThat(getResponse).isNotNull();
    System.out.println(getResponse);
    String fileContent = getJSONFromFile("src/test/resources/customer.json");
    JSONObject jsonObj = new JSONObject(fileContent);
    JSONObject adress = jsonObj.getJSONObject("address");
    assertThat(getResponse.getBody()).isEqualTo(adress.toString());
  }

  private HttpEntity<String> createGetRequest() {

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.ACCEPT, "application/json");
    headers.add(HttpHeaders.CONNECTION, "keep-alive");
    HttpEntity<String> getRequest = new HttpEntity<>(headers);
    return getRequest;
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
