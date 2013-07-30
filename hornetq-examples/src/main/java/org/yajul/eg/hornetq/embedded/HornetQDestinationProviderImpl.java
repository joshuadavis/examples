package org.yajul.eg.hornetq.embedded;

import org.hornetq.jms.client.HornetQQueue;
import org.hornetq.jms.client.HornetQTopic;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Provides destination references using HornetQ classes.
 * <br>
 * User: josh
 * Date: 7/30/13
 * Time: 12:57 PM
 */
public class HornetQDestinationProviderImpl implements DestinationProvider
{
    @Override
    public Queue getQueue(String name)
    {
        return new HornetQQueue(name);
    }

    @Override
    public Topic getTopic(String name)
    {
        return new HornetQTopic(name);
    }
}
