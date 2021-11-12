package com.crazynerve.grpctutorials.services;

import com.crazynerve.grpctutorials.models.BoolResponse;
import com.crazynerve.grpctutorials.models.Number;
import com.crazynerve.grpctutorials.models.NumberList;
import com.crazynerve.grpctutorials.models.NumberServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;


public class NumberServiceImpl extends NumberServiceGrpc.NumberServiceImplBase
{

    private static final Logger LOGGER = Logger.getLogger( NumberServiceImpl.class.getName() );


    // unary
    @Override
    public void checkIfPrime( Number request, StreamObserver<BoolResponse> responseObserver )
    {
        responseObserver.onNext( BoolResponse.newBuilder().setResult( isPrime( request.getNumber() ) ).build() );
        responseObserver.onCompleted();
    }


    // server side streaming
    @Override
    public void findPrimes( Number request, StreamObserver<Number> responseObserver )
    {
        if ( request.getNumber() < 1 ) {
            Status status = Status.FAILED_PRECONDITION.withDescription( "Number should be greater than 1" );
            responseObserver.onError( status.asRuntimeException() );
            return;
        }
        IntStream.range( 2, request.getNumber() ).filter( this::isPrime ).boxed().forEach( ( n ) -> {
            /*
            try {
                Thread.sleep( 500 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }*/
            responseObserver.onNext( Number.newBuilder().setNumber( n ).build() );
        } );

        responseObserver.onCompleted();
    }


    // client side streaming
    @Override
    public StreamObserver<Number> primeList( StreamObserver<NumberList> responseObserver )
    {
        final List<Integer> primeNumberList = new ArrayList<>();
        // implementing request
        return new StreamObserver<Number>()
        {

            @Override
            public void onNext( Number number )
            {
                // implement what the server should do once it keeps getting requests
                if ( isPrime( number.getNumber() ) ) {
                    primeNumberList.add( number.getNumber() );
                }
            }


            @Override
            public void onError( Throwable throwable )
            {

            }


            @Override
            public void onCompleted()
            {
                responseObserver.onNext( NumberList.newBuilder().addAllNumbers( primeNumberList ).build() );
                responseObserver.onCompleted();
            }
        };
    }


    // Bi directional streaming
    @Override
    public StreamObserver<Number> primeStream( StreamObserver<Number> responseObserver )
    {
        return new StreamObserver<Number>()
        {
            @Override
            public void onNext( Number number )
            {
                LOGGER.info( () -> "Got request for " + number.getNumber() );
                if ( isPrime( number.getNumber() ) ) {
                    responseObserver.onNext( number );
                }
            }


            @Override
            public void onError( Throwable throwable )
            {

            }


            @Override
            public void onCompleted()
            {
                LOGGER.info( () -> "Got completed signal." );
                responseObserver.onCompleted();
            }
        };
    }


    private boolean isPrime( int number )
    {
        return number > 1 && IntStream.range( 2, number ).noneMatch( index -> number % index == 0 );
    }
}
