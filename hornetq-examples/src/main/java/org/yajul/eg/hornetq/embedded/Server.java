package org.yajul.eg.hornetq.embedded;

import org.hornetq.jms.server.embedded.EmbeddedJMS;

/**
 * HornetQ server lifecycle.
 * <br>
 * User: josh
 * Date: 1/9/13
 * Time: 10:48 PM
 */
public class Server
{
    public static void main(String[] args)
    {
        try
        {
            startServer();
            LocalClient localClient = new LocalClient();
            localClient.go();

            LocalJMSClient localJMSClient = new LocalJMSClient();
            localJMSClient.go();
        }
        catch (Exception e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static void startServer()
    {
        try
        {
            // Start the JMS Server using the HornetQ core server and the JMS configuration
            // By default, this uses hornetq-configuration.xml, hornetq-jms.xml, and hornetq-users.xml
            EmbeddedJMS jmsServer = new EmbeddedJMS();
            jmsServer.start();
            System.out.println("Started Embedded JMS Server");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
