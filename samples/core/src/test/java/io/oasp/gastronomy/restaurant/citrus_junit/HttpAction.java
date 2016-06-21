package io.oasp.gastronomy.restaurant.citrus_junit;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestDesigner;
import com.consol.citrus.message.MessageType;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */

// @RunWith(SpringJUnit4ClassRunner.class)
// @SpringApplicationConfiguration(classes = { SpringBootApp.class })
// @WebIntegrationTest("server.port:8090")
// @ActiveProfiles(profiles = { OaspProfile.JUNIT_TEST })
// @Transactional
public class HttpAction extends JUnit4CitrusTestDesigner {
  @Test
  @CitrusTest
  public void httpActionTest() {

    // variable("custom_header_id", 3);
    // http().client("salesmanagementClient").get("table/101").payload("").header("").contentType("application/json")
    // .accept("application/json, */*");

    // http().client("salesmanagementClient").get("table/101").contentType("application/json")
    // .accept("application/json, */*");

    // http().client("salesmanagementClientPost").post("order/search").payload("{state: \"OPEN\", tableId: \"101\"}")
    // .header("").contentType("application/json").accept("application/json, text/plain, */*");
    // http().client("salesmanagementClientPost").response(HttpStatus.OK).payload("").header("").version("HTTP/1.1");
    //
    send("salesmanagementClientPost").payload("{state: \"OPEN\", tableId: \"101\"}").http().method(HttpMethod.POST)
        .path("order/search");
    receive("salesmanagementClientPost").messageType(MessageType.JSON).http().status(HttpStatus.OK);

  }
}
