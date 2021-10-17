package com.crazynerve.grpctutorials;

import com.crazynerve.grpctutorials.services.NumberServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        Server server = ServerBuilder.forPort( 5050 )
            .addService( new NumberServiceImpl() )
            .build();
        server.start();
        server.awaitTermination();
    }
}
