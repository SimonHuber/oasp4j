package io.oasp.gastronomy.restaurant.citrus_junit;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestDesigner;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */
public class HttpAction extends JUnit4CitrusTestDesigner {
  @Test
  @CitrusTest
  public void httpActionTest() {

    http().client("httpClient").post("/customer")
        .payload("<customer>" + "<id>citrus:randomNumber()</id>" + "<name>testuser</name>" + "</customer>")
        .header("CustomHeaderId", "${custom_header_id}").contentType("text/xml").accept("text/xml, */*");

    http().client("httpClient").response(HttpStatus.OK)
        .payload("<customerResponse>" + "<success>true</success>" + "</customerResponse>")
        .header("CustomHeaderId", "${custom_header_id}").version("HTTP/1.1");
  }
}
