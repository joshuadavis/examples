package org.yajul.eg.hornetq.embedded;

import org.hornetq.jms.client.HornetQQueue;

import javax.jms.*;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Local (in JVM) client.
 * <br>
 * User: josh
 * Date: 1/10/13
 * Time: 6:52 AM
 */
public class LocalJMSClient
{
    private static final Logger log = Logger.getLogger(LocalJMSClient.class.getName());

    public void go() throws Exception
    {
        final Queue queue = new HornetQDestinationProviderImpl().getQueue("queue.exampleJmsQueue");
        ConnectionFactory cf = new LocalInVMConnectionFactoryProvider().getConnectionFactory();
        Connection connection = null;
        Session session = null;

        try
        {
            connection = cf.createConnection();

            // Step 5. Create the session, and producer

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(queue);

            // Step 6. Create and send a message
            TextMessage message = session.createTextMessage();

            final String propName = "myprop";

            message.setStringProperty(propName, "Hello sent at " + new Date());

            log.info("Sending the message.");

            producer.send(message);

            // Step 7. Create the message consumer and start the connection
            MessageConsumer messageConsumer = session.createConsumer(queue);
            connection.start();

            // Step 8. Receive the message.
            Message messageReceived = messageConsumer.receive(1000);
            log.info("Received TextMessage:" + messageReceived.getStringProperty(propName));
        }
        finally
        {
            // Step 9. Be sure to close our resources!
            if (session != null)
                session.close();
            if (connection != null)
                connection.close();
        }
    }
}
