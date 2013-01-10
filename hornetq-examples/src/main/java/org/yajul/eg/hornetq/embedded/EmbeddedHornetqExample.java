package org.yajul.eg.hornetq.embedded;

/**
 * Example: start up embedded hornetq, with remote access.
 * <br>
 * User: josh
 * Date: 1/9/13
 * Time: 10:32 PM
 */
public class EmbeddedHornetqExample
{
    public static void main(String[] args)
    {
        EmbeddedHornetqExample ex = new EmbeddedHornetqExample();
        ex.go();
    }

    private void go()
    {
        Server server = new Server();
        server.start();
    }
}
