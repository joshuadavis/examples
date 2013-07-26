package org.yajul.eg.hornetq.embedded;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.config.Configuration;
import org.hornetq.core.config.impl.ConfigurationImpl;
import org.hornetq.core.remoting.impl.invm.InVMAcceptorFactory;
import org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory;
import org.hornetq.core.server.HornetQServer;
import org.hornetq.core.server.HornetQServers;

import java.util.HashSet;

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
            // Step 1. Create the Configuration, and set the properties accordingly
            Configuration configuration = new ConfigurationImpl();
            //we only need this for the server lock file
            configuration.setJournalDirectory("target/data/journal");
            configuration.setPersistenceEnabled(false);
            configuration.setSecurityEnabled(false);

            TransportConfiguration remoteTransport = new TransportConfiguration(NettyAcceptorFactory.class.getName());
            TransportConfiguration localTransport = new TransportConfiguration(InVMAcceptorFactory.class.getName());
            HashSet<TransportConfiguration> setTransp = new HashSet<TransportConfiguration>();
            setTransp.add(remoteTransport);
            setTransp.add(localTransport);
            configuration.setAcceptorConfigurations(setTransp);

            // Step 2. Create and start the server
            HornetQServer server = HornetQServers.newHornetQServer(configuration);
            server.start();
            System.out.println("HornetQ Server started.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
