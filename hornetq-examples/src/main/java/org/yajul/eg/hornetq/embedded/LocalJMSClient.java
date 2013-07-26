package org.yajul.eg.hornetq.embedded;

import org.hornetq.api.core.SimpleString;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.*;
import org.hornetq.core.remoting.impl.invm.InVMConnectorFactory;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;
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
        // Step 3. As we are not using a JNDI environment we instantiate the objects directly
        ServerLocator serverLocator = HornetQClient.createServerLocatorWithoutHA(new TransportConfiguration(InVMConnectorFactory.class.getName()));
        ClientSessionFactory sf = serverLocator.createSessionFactory();

        // Step 4. Create a core queue
        ClientSession coreSession = sf.createSession(false, false, false);

        final String queueName = "queue.exampleJmsQueue";
        final HornetQQueue queue = new HornetQQueue(queueName);

        log.info("Querying queue...");
        ClientSession.QueueQuery q = coreSession.queueQuery(new SimpleString(queueName));
        log.info("q.exists=" + q.isExists());
        if (!q.isExists())
        {
            coreSession.createQueue(queue.getAddress(), queue.getAddress(), true);
        }

        ClientSession.BindingQuery b = coreSession.bindingQuery(new SimpleString(queueName));
        log.info("Queue names=" + b.getQueueNames());

        coreSession.close();

        ConnectionFactory cf = new HornetQJMSConnectionFactory(serverLocator);
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
