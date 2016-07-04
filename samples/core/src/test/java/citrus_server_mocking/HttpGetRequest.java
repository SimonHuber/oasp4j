package citrus_server_mocking;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.dsl.design.TestDesigner;

import io.oasp.gastronomy.restaurant.general.common.base.AbstractRestServiceTest;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */

public class HttpGetRequest extends AbstractRestServiceTest {

  /**
   * @param args
   * @throws IOException
   */

  @Test
  public void httpGetRequestActionTest(@CitrusResource TestDesigner designer) {

    RestTemplate template = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    headers.add("Connection", "keep-alive");
    HttpEntity<String> getRequest = new HttpEntity<>(headers);
    ResponseEntity<String> getResponse =
        template.exchange("http://localhost:8081/test", HttpMethod.GET, getRequest, String.class);
    assertThat(getResponse).isNotNull();

    byte[] encodedFileContent = null;
    try {
      encodedFileContent = Files.readAllBytes(Paths.get("src/test/resources/orderPositionPayload.json"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String fileContent = new String(encodedFileContent, Charset.defaultCharset());

    assertThat(getResponse).isEqualTo(new ClassPathResource("orderPositionPayload.json"));
    assertThat(getResponse).isEqualTo(fileContent);
    System.out.println(getResponse.getBody());

    // RestTemplate template = new RestTemplate();
    //
    // HttpHeaders headers = new HttpHeaders();
    // // headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    // headers.add("Connection", "keep-alive");
    // HttpEntity<String> getRequest = new HttpEntity<>(headers);
    // ResponseEntity<String> getResponse =
    // template.exchange("http://localhost:8081/test", HttpMethod.GET, getRequest, String.class);
    // assertThat(getResponse.getBody()).isNotNull();
    // // URL url = getClass().getResource("orderPositionPayload.json");
    //
    // assertThat(getResponse.getBody().toString())
    // .isEqualTo(FileUtils.readLines(new File("src/test/resources/orderPositionPayload.json")));
    // System.out.println(getResponse.getBody());

  }
}
