package citrusserver;

import static citrusserver.ServerMockHelper.GET_ALL_CUSTOMER_DATA;
import static citrusserver.ServerMockHelper.GET_CUSTOMER_ADDRESS;
import static citrusserver.ServerMockHelper.GET_ORDER_POSITION;
import static citrusserver.ServerMockHelper.GET_ORDER_POSITIONS;
import static citrusserver.ServerMockHelper.RESOURCE_PATH;
import static citrusserver.ServerMockHelper.getJSONFromFile;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HttpGetRequestTests extends Assertions {

  /**
   * @param args
   * @throws IOException
   */

  private static RestTemplate template = new RestTemplate();

  private static final int port = 8081;

  private static final String SERVER_URL = "http://localhost:";

  @Test
  public void getOrderPosition() {

    HttpEntity<String> getRequest = createGetRequest();
    ResponseEntity<String> getResponse =
        template.exchange(SERVER_URL + port + GET_ORDER_POSITION, HttpMethod.GET, getRequest, String.class);
    assertThat(getResponse).isNotNull();
    String fileContent = getJSONFromFile(RESOURCE_PATH + "orderPositionPayload.json");
    assertThat(fileContent).isNotNull();
    assertThat(getResponse.getBody()).isEqualTo(fileContent);
  }

  @Test
  public void getOrderPositions() {

    HttpEntity<String> getRequest = createGetRequest();
    ResponseEntity<String> getResponse =
        template.exchange(SERVER_URL + port + GET_ORDER_POSITIONS, HttpMethod.GET, getRequest, String.class);
    assertThat(getResponse).isNotNull();
    String fileContent = getJSONFromFile(RESOURCE_PATH + "orderPositionsPayload.json");
    assertThat(fileContent).isNotNull();
    assertThat(getResponse.getBody()).isEqualTo(fileContent);
  }

  @Test
  public void getAllCustomerDates() {

    HttpEntity<String> getRequest = createGetRequest();
    ResponseEntity<String> getResponse =
        template.exchange(SERVER_URL + port + GET_ALL_CUSTOMER_DATA, HttpMethod.GET, getRequest, String.class);
    assertThat(getResponse).isNotNull();
    String fileContent = getJSONFromFile(RESOURCE_PATH + "customer.json");
    assertThat(getResponse.getBody()).isEqualTo(fileContent);
  }

  @Test
  public void getCustomerAddress() {

    HttpEntity<String> getRequest = createGetRequest();
    ResponseEntity<String> getResponse =
        template.exchange(SERVER_URL + port + GET_CUSTOMER_ADDRESS, HttpMethod.GET, getRequest, String.class);
    assertThat(getResponse).isNotNull();
    String fileContent = getJSONFromFile(RESOURCE_PATH + "customer.json");
    JSONObject jsonObject = new JSONObject(fileContent);
    JSONObject address = jsonObject.getJSONObject("address");
    assertThat(getResponse.getBody()).isEqualTo(address.toString());
  }

  private HttpEntity<String> createGetRequest() {

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.ACCEPT, "application/json");
    HttpEntity<String> getRequest = new HttpEntity<>(headers);
    return getRequest;
  }
}
