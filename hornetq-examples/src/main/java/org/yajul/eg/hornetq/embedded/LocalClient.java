package org.yajul.eg.hornetq.embedded;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.*;
import org.hornetq.core.remoting.impl.invm.InVMConnectorFactory;

import java.util.Date;

/**
 * Local (in JVM) client.
 * <br>
 * User: josh
 * Date: 1/10/13
 * Time: 6:52 AM
 */
public class LocalClient
{
    public void go() throws Exception
    {
        // Step 3. As we are not using a JNDI environment we instantiate the objects directly
        ServerLocator serverLocator = HornetQClient.createServerLocatorWithoutHA(new TransportConfiguration(InVMConnectorFactory.class.getName()));
        try
        {
            example(serverLocator);
        }
        finally
        {
            if (serverLocator != null)
                serverLocator.close();
        }
    }

    private void example(ServerLocator serverLocator) throws Exception
    {
        ClientSessionFactory sf = serverLocator.createSessionFactory();

        // Step 4. Create a core queue
        ClientSession coreSession = sf.createSession(false, false, false);

        final String queueName = "queue.exampleQueue";

        coreSession.createQueue(queueName, queueName, true);

        coreSession.close();

        ClientSession session = null;

        try
        {

            // Step 5. Create the session, and producer
            session = sf.createSession();

            ClientProducer producer = session.createProducer(queueName);

            // Step 6. Create and send a message
            ClientMessage message = session.createMessage(false);

            final String propName = "myprop";

            message.putStringProperty(propName, "Hello sent at " + new Date());

            System.out.println("Sending the message.");

            producer.send(message);

            // Step 7. Create the message consumer and start the connection
            ClientConsumer messageConsumer = session.createConsumer(queueName);
            session.start();

            // Step 8. Receive the message.
            ClientMessage messageReceived = messageConsumer.receive(1000);
            System.out.println("Received TextMessage:" + messageReceived.getStringProperty(propName));
        }
        finally
        {
            // Step 9. Be sure to close our resources!
            sf.close();
        }
    }
}
