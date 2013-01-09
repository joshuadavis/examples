package org.yajul.eg.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * TODO: Add class level comments.
 * <br>
 * User: josh
 * Date: 1/9/13
 * Time: 7:50 AM
 */
public class KryoExample1
{
    public static enum Enum1
    {
        ONE,
        TWO,
        THREE
    }

    private static int counter = 0;

    public static class Bar implements Serializable
    {
        int id = counter++;
        String name = "fred" + System.currentTimeMillis();
        Double iamnull = null;
//        List<Enum1> list = Collections.unmodifiableList(Arrays.asList(Enum1.ONE,Enum1.THREE));
        List<Enum1> list = Arrays.asList(Enum1.ONE, Enum1.THREE);
    }

    public static class Foo implements Serializable
    {
        int x = 100;
        int y = 200;
        double z = 3.1415;
        Enum1 e = Enum1.ONE;
        String s = "hello there!";
        List<Bar> bars = Arrays.asList(new Bar(), new Bar(), new Bar());
    }

    public static void main(String[] args)
    {
        KryoExample1 ex = new KryoExample1();
        ex.go();
    }

    private byte[] toBytes(Object o)
    {
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(baos);
            out.writeObject(o);
            out.close();
            return baos.toByteArray();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void go()
    {
        Foo f = new Foo();
        byte[] bytes = toBytes(f);
        System.out.println("bytes.length=" + bytes.length);

        byte[] kryoBytes = toKryoBytes(f);
        System.out.println("kryoBytes.length=" + kryoBytes.length);
    }

    private byte[] toKryoBytes(Object o)
    {
        Kryo kryo = new Kryo();
        int nextid = kryo.getNextRegistrationId();
        kryo.register(Enum1.class,nextid++);
        kryo.register(Foo.class,nextid++);
        kryo.register(Bar.class,nextid++);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output out = new Output(baos);
        kryo.writeObject(out,o);
        out.close();
        return baos.toByteArray();
    }
}
