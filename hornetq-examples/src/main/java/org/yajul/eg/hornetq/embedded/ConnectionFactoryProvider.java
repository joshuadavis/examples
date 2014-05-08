package org.yajul.eg.hornetq.embedded;

import javax.jms.ConnectionFactory;

/**
 * Implementations provide JMS ConnectionFactory instances.
 * <br>
 * User: josh
 * Date: 7/30/13
 * Time: 12:26 PM
 */
public interface ConnectionFactoryProvider
{
    ConnectionFactory getConnectionFactory();
}
