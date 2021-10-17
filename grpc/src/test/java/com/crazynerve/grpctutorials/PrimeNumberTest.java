package com.crazynerve.grpctutorials;

import com.crazynerve.grpctutorials.models.BoolResponse;
import com.crazynerve.grpctutorials.models.Number;
import com.crazynerve.grpctutorials.models.NumberList;
import com.crazynerve.grpctutorials.models.NumberServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;


@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class PrimeNumberTest
{
    private static final Logger LOGGER = Logger.getLogger( PrimeNumberTest.class.getName() );
    NumberServiceGrpc.NumberServiceBlockingStub blockingStub;
    NumberServiceGrpc.NumberServiceStub stub;

    @BeforeAll
    public void setUp(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress( "localhost", 5050 )
            .usePlaintext().build();
        blockingStub = NumberServiceGrpc.newBlockingStub( channel );
        stub = NumberServiceGrpc.newStub( channel );
    }

    // Unary
    @Test
    public void testIfPrime(){
        int num = 97;
        Number number = Number.newBuilder().setNumber( num ).build();
        BoolResponse response = blockingStub.checkIfPrime( number );
        LOGGER.info( () -> "Is " + num + " prime : " + response.getResult());
    }

    // Unary Async
    @Test
    public void testIfPrimeAsync() throws InterruptedException
    {
        int num = 13;
        CountDownLatch latch = new CountDownLatch( 1 );
        Number number = Number.newBuilder().setNumber( num ).build();
        stub.checkIfPrime( number, new StreamObserver<BoolResponse>()
        {
            @Override
            public void onNext( BoolResponse boolResponse )
            {
                LOGGER.info( () -> "Is " + num + " prime : " + boolResponse.getResult()  );
            }


            @Override
            public void onError( Throwable throwable )
            {
                latch.countDown();
            }


            @Override
            public void onCompleted()
            {
                latch.countDown();
            }
        } );
        latch.await();
    }

    // Server side streaming
    @Test
    public void testPrimeNumbersBlockingStub(){
        Number number = Number.newBuilder().setNumber( 100 ).build();
        blockingStub.findPrimes( number )
            .forEachRemaining( n -> LOGGER.info( () -> "" + n.getNumber() ) );
    }


    // Server side streaming error
    @Test
    public void testPrimeNumbersBlockingStubIllegalInput(){
        Number number = Number.newBuilder().setNumber( -1 ).build();
        blockingStub.findPrimes( number )
            .forEachRemaining( n -> LOGGER.info( () -> "" + n.getNumber() ) );
    }

    // Server side streaming async
    @Test
    public void testPrimeNumbersAsyncStub() throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(1);
        Number number = Number.newBuilder().setNumber( 100 ).build();
        stub.findPrimes( number, new StreamObserver<Number>()
        {
            @Override
            public void onNext( Number number )
            {
                LOGGER.info( () -> "" + number.getNumber() );
            }


            @Override
            public void onError( Throwable throwable )
            {
                LOGGER.throwing( "", "", throwable );
                latch.countDown();
            }


            @Override
            public void onCompleted()
            {
                LOGGER.info( () -> "That's it, Folks !!!" );
                latch.countDown();
            }
        } );
        latch.await();
    }

    // Client side streaming
    @Test
    public void testClientStreamClientStub() throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch( 1 );
        StreamObserver<Number> streamObserver = stub.primeList( new StreamObserver<NumberList>()
        {
            @Override
            public void onNext( NumberList numberList )
            {
                numberList.getNumbersList().forEach( n -> LOGGER.info( () -> "" + n ) );
            }


            @Override
            public void onError( Throwable throwable )
            {
                latch.countDown();
            }


            @Override
            public void onCompleted()
            {
                LOGGER.info( "That's it folks!!!" );
                latch.countDown();
            }
        } );
        for (int i = 2; i < 100; i++){
            Number number = Number.newBuilder().setNumber( i ).build();
            streamObserver.onNext( number );
        }
        streamObserver.onCompleted();
        latch.await();
    }

    // Bi Directional streaming
    @Test
    public void testBiDirectionalStreaming() throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch( 1 );
        StreamObserver<Number> observer = stub.primeStream( new StreamObserver<Number>()
        {
            @Override
            public void onNext( Number number )
            {
                LOGGER.info( () -> "" + number.getNumber() );
            }


            @Override
            public void onError( Throwable throwable )
            {
                latch.countDown();
            }


            @Override
            public void onCompleted()
            {
                LOGGER.info( () -> "That's it folks!!!" );
                latch.countDown();
            }
        } );
        int num = 100;
        for(int i =2; i< 100; i ++){
            observer.onNext( Number.newBuilder().setNumber( i ).build() );
        }
        observer.onCompleted();
        latch.await();
    }
}
