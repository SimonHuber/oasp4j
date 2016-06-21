package io.oasp.gastronomy.restaurant.citrus_junit;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestDesigner;
import com.consol.citrus.message.MessageType;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.module.basic.configuration.SpringProfileConstants;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */

// @RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { SpringBootApp.class })
@WebIntegrationTest("server.port:8090")
@ActiveProfiles(profiles = { SpringProfileConstants.JUNIT })

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
    //
    //

    // send("salesmanagementClientPost").payload("{state: \"OPEN\", tableId: \"101\"}").http().method(HttpMethod.POST)
    // .header("Authorization", getAuthentificatedHeaders()).path("order/search");
    // receive("salesmanagementClientPost").messageType(MessageType.JSON).http().status(HttpStatus.OK);

    send("salesmanagementClient").http().method(HttpMethod.GET)
        .header(HttpHeaders.AUTHORIZATION, getAuthentificatedHeaders()).path("order/");
    receive("salesmanagementClient").messageType(MessageType.JSON).http().status(HttpStatus.OK);

  }

  private String getAuthentificatedHeaders() {

    String plainCreds = "waiter" + ":" + "waiter";
    byte[] plainCredsBytes = plainCreds.getBytes();
    byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
    String base64Creds = new String(base64CredsBytes);
    echo("\n\n\nBasic " + base64Creds + "\n\n\n");
    return "Basic " + base64Creds;
  }
}
