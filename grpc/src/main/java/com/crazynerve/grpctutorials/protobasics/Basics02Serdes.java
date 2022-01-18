package com.crazynerve.grpctutorials.protobasics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Basics02Serdes
{
    public static void main( String[] args ) throws IOException
    {
        Person johnDoe = Person.newBuilder()
            .setName( "John Doe" )
            .setAge( 15 )
            .build();

        // write to a file
        Path path = Paths.get( "john.ser" );
        Files.write(path, johnDoe.toByteArray());

        // read from file
        byte[] bytes = Files.readAllBytes( path );
        Person johnDoeRevived = Person.parseFrom( bytes );
        System.out.println("John Doe Revived : " + johnDoeRevived);

    }
}
