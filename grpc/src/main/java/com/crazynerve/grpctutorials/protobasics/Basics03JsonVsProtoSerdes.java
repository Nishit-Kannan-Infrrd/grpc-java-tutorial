package com.crazynerve.grpctutorials.protobasics;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;


public class Basics03JsonVsProtoSerdes
{
    public static void main( String[] args )
    {
        // json serdes
        Person4Json person4Json = new Person4Json( "John Doe", 15 );
        ObjectMapper objectMapper = new ObjectMapper();
        Runnable jsonRunnable = () -> {
            try {
                // serialize
                byte[] bytes = objectMapper.writeValueAsBytes( person4Json );
                // desrialize
                objectMapper.readValue( bytes, Person4Json.class );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        };
        run( jsonRunnable, "JSON" );

        // proto serdes
        Person person4Proto = Person.newBuilder().setName( "John Doe" ).setAge( 15 ).build();
        Runnable protoRunnable = () -> {
            // serialize
            byte[] bytes = person4Proto.toByteArray();
            // deserialize
            try {
                Person.parseFrom( bytes );
            } catch ( InvalidProtocolBufferException e ) {
                e.printStackTrace();
            }
        };
        run( protoRunnable, "PROTO" );

    }


    public static void run( Runnable runnable, String method )
    {
        long startTime = System.currentTimeMillis();
        for ( int i = 0; i < 50000000; i++ ) {
            runnable.run();
        }
        long endTime = System.currentTimeMillis();
        System.out.println( "Time taken for " + method + " is " + ( endTime - startTime ) / 1000 + " seconds." );
    }
}


class Person4Json
{
    private String name;
    private int age;


    public Person4Json()
    {
    }


    public Person4Json( String name, int age )
    {
        this.name = name;
        this.age = age;
    }


    public String getName()
    {
        return name;
    }


    public void setName( String name )
    {
        this.name = name;
    }


    public int getAge()
    {
        return age;
    }


    public void setAge( int age )
    {
        this.age = age;
    }


    @Override
    public String toString()
    {
        return "Person4Json{" + "name='" + name + '\'' + ", age='" + age + '\'' + '}';
    }
}
