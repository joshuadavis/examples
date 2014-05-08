package org.yajul.eg.hornetq.embedded;

import org.hornetq.jms.server.embedded.EmbeddedJMS;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO: Add class level comments.
 * <br>
 * User: josh
 * Date: 7/29/13
 * Time: 1:45 PM
 */
public class SharedListenerExample
{
    private static final Logger log = Logger.getLogger(SharedListenerExample.class.getName());

    public static class Server
    {
        private EmbeddedJMS jmsServer;

        public void start()
        {
            // Start the JMS Server using the HornetQ core server and the JMS configuration
            // By default, this uses hornetq-configuration.xml, hornetq-jms.xml, and hornetq-users.xml
            synchronized (this)
            {
                if (jmsServer != null)
                    throw new IllegalStateException("The server is already running!");
                EmbeddedJMS server;
                try
                {
                    server = new EmbeddedJMS();
                    log.info("Starting server...");
                    server.start();
                }
                catch (Exception e)
                {
                    log.log(Level.SEVERE,"Unable to start due to " + e.getMessage(), e);
                    throw new RuntimeException(e);
                }
                jmsServer = server;
            }
        }

        public void stop()
        {
            synchronized (this)
            {
                doStop();
            }
        }

        private void doStop()
        {
            EmbeddedJMS s = jmsServer;
            jmsServer = null;
            if (s != null)
            {
                try
                {
                    s.stop();
                }
                catch (Exception e)
                {
                    log.log(Level.SEVERE,"Unable to stop due to " + e.getMessage(), e);
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public static class SharedListener
    {
        public void start()
        {
            try
            {
                Properties props = new Properties();
                props.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
                props.put("java.naming.provider.url", "jnp://localhost:1099");
                props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
                InitialContext ic = new InitialContext(props);
                NamingEnumeration<NameClassPair> e =  ic.list("");
                while (e.hasMore())
                {
                    NameClassPair ncp = e.next();
                    log.info("ncp=" + ncp);
                }
            }
            catch (Exception e)
            {
                log.log(Level.SEVERE,"Unexpected: " + e, e);
            }
        }

        public void stop()
        {

        }
    }

    public static void main(String[] args)
    {
        try
        {
            log.info("Here we go...");
            Server server = new Server();
            server.start();


            SharedListener sharedListener = new SharedListener();
            sharedListener.start();

            // Publish some messages, make sure they are received.

            server.stop();

        }
        catch (Exception e)
        {
            log.log(Level.SEVERE,"Unexpected: " + e, e);
        }
    }
}
