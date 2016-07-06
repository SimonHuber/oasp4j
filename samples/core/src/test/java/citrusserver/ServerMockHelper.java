package citrusserver;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */
public class ServerMockHelper {
  protected static final String GET_ORDER_POSITION = "/orderposition";

  protected static final String GET_ORDER_POSITIONS = "/orderpositions";

  protected static final String GET_ALL_CUSTOMER_DATA = "/allcustomerdates";

  protected static final String GET_CUSTOMER_ADDRESS = "/customeraddress";

  protected static final String ROLE = "waiter";

  protected static final String RESOURCE_PATH = "src/test/resources/";

  protected static String getJSONFromFile(String fileName) {

    byte[] encodedFileContent = null;
    try {
      encodedFileContent = Files.readAllBytes(Paths.get(fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String fileContent = new String(encodedFileContent, Charset.defaultCharset());
    return fileContent;
  }
}
