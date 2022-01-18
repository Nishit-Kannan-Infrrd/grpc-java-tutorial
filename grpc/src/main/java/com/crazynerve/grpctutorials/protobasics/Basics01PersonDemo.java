package com.crazynerve.grpctutorials.protobasics;

public class Basics01PersonDemo
{
    public static void main( String[] args )
    {
        Person johnDoe = Person.newBuilder()
            .setName( "John Doe" )
            .setAge( 15 )
            .build();
        System.out.println("Person : " + johnDoe );
    }
}
