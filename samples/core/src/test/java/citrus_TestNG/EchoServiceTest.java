package citrus_TestNG;

import java.net.MalformedURLException;
import java.util.Queue;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.connection.SingleConnectionFactory;

import com.consol.citrus.Citrus;
import com.consol.citrus.annotations.CitrusFramework;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.builder.AbstractTestActionBuilder;
import com.consol.citrus.dsl.design.TestDesigner;
import com.consol.citrus.jms.endpoint.JmsSyncEndpoint;
import com.consol.citrus.jms.endpoint.JmsSyncEndpointConfiguration;
import com.consol.citrus.jms.message.JmsMessage;
import com.consol.citrus.message.MessageType;

/**
 * TODO shuber This type ...
 *
 * @author shuber
 * @since dev
 */
@RunWith(Arquillian.class)
public class EchoServiceTest {

  @CitrusFramework
  private Citrus citrusFramework;

  @Resource(mappedName = "jms/queue/test")
  private Queue echoQueue;

  @Resource(mappedName = "/ConnectionFactory")
  private ConnectionFactory connectionFactory;

  private JmsSyncEndpoint jmsSyncEndpoint;

  @Deployment
  @OverProtocol("Servlet 3.0")
  public static WebArchive createDeployment() throws MalformedURLException {

    return ShrinkWrap.create(WebArchive.class).addClasses(EchoService.class);
  }

  @Before
  public void setUp() {

    JmsSyncEndpointConfiguration endpointConfiguration = new JmsSyncEndpointConfiguration();
    endpointConfiguration.setConnectionFactory(new SingleConnectionFactory(this.connectionFactory));
    endpointConfiguration.setDestination((Destination) this.echoQueue);
    this.jmsSyncEndpoint = new JmsSyncEndpoint(endpointConfiguration);
  }

  @After
  public void cleanUp() {

    closeConnections();
  }

  @Test
  @CitrusTest
  public void shouldBeAbleToSendMessage(@CitrusResource TestDesigner designer) throws Exception {

    String messageBody = "ping";

    designer.send(this.jmsSyncEndpoint).messageType(MessageType.PLAINTEXT).message(new JmsMessage(messageBody));

    designer.receive(this.jmsSyncEndpoint).messageType(MessageType.PLAINTEXT).message(new JmsMessage(messageBody));

    this.citrusFramework.run(((AbstractTestActionBuilder) designer).build());
  }

  private void closeConnections() {

    ((SingleConnectionFactory) this.jmsSyncEndpoint.getEndpointConfiguration().getConnectionFactory()).destroy();
  }
}