package org.yajul.eg.hornetq.embedded;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.HornetQClient;
import org.hornetq.api.core.client.ServerLocator;
import org.hornetq.core.remoting.impl.invm.InVMConnectorFactory;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.jms.client.HornetQJMSConnectionFactory;

import javax.jms.ConnectionFactory;

/**
 * Provides JMS ConnectionFactory instances for a local, in-vm server.
 * <br>
 * User: josh
 * Date: 7/30/13
 * Time: 12:30 PM
 */
public class RemoteConnectionFactoryProvider implements ConnectionFactoryProvider
{
    private final ServerLocator serverLocator;
    private final HornetQJMSConnectionFactory cf;

    public RemoteConnectionFactoryProvider()
    {
       serverLocator = HornetQClient.createServerLocatorWithoutHA(
               new TransportConfiguration(NettyConnectorFactory.class.getName()));
        cf = new HornetQJMSConnectionFactory(serverLocator);
    }

    @Override
    public ConnectionFactory getConnectionFactory()
    {
        return cf;
    }
}
