package org.yajul.eg.hornetq.embedded;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Provides JMS Queue and Topic objects.
 * <br>
 * User: josh
 * Date: 7/30/13
 * Time: 12:27 PM
 */
public interface DestinationProvider
{
    Queue getQueue(String name);

    Topic getTopic(String name);
}
