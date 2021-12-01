package com.crazynerve.grpctutorials;

import com.crazynerve.grpctutorials.services.NumberServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;


/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger LOGGER = Logger.getLogger( App.class.getName() );

    public static void main( String[] args ) throws IOException, InterruptedException
    {
        Server server = ServerBuilder.forPort( 5050 )
            .addService( new NumberServiceImpl() )
            .build();
        LOGGER.info( () -> "Starting server..." );
        server.start();
        LOGGER.info( () -> "Server started..." );
        server.awaitTermination();
    }
}
