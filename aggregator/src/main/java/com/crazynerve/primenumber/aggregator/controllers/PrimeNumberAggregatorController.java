package com.crazynerve.primenumber.aggregator.controllers;

import com.crazynerve.grpctutorials.models.Number;
import com.crazynerve.grpctutorials.models.NumberList;
import com.crazynerve.grpctutorials.models.NumberServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RestController
@RequestMapping ("/aggregate")
public class PrimeNumberAggregatorController
{
    private ManagedChannel managedChannel;

    private RestTemplate restTemplate = new RestTemplate();
    private NumberServiceGrpc.NumberServiceBlockingStub blockingStub;
    private NumberServiceGrpc.NumberServiceStub stub;


    @Autowired
    public void setManagedChannel( ManagedChannel managedChannel )
    {
        this.managedChannel = managedChannel;
    }


    @PostConstruct
    public void setUp()
    {
        blockingStub = NumberServiceGrpc.newBlockingStub( managedChannel );
        stub = NumberServiceGrpc.newStub( managedChannel );
    }


    @GetMapping ("/rest/{number}")
    public ResponseEntity<List<Integer>> restCall( @PathVariable ("number") int number )
    {
        String baseUrl = "http://localhost:8080/rest/prime/";
        List<Integer> primeList = IntStream.range( 2, number ).boxed().filter( n -> {
            boolean result = false;
            ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity( baseUrl + n, Boolean.class );
            if ( responseEntity.getStatusCode() == HttpStatus.OK ) {
                result = responseEntity.getBody();
            }
            return result;
        } ).collect( Collectors.toList() );
        return new ResponseEntity<>( primeList, HttpStatus.OK );
    }


    @GetMapping ("/unary/{number}")
    public ResponseEntity<List<Integer>> unaryGrpcCall( @PathVariable ("number") int number )
    {
        List<Integer> primeList = IntStream.range( 2, number ).boxed()
            .filter( n -> blockingStub.checkIfPrime( Number.newBuilder().setNumber( n ).build() ).getResult() )
            .collect( Collectors.toList() );
        return new ResponseEntity<>( primeList, HttpStatus.OK );
    }


    @GetMapping ("/serverstream/sync/{number}")
    public ResponseEntity<List<Integer>> serverStreamSyncGrpcCall( @PathVariable ("number") int number )
    {
        List<Integer> primeList = new ArrayList<>();
        blockingStub.findPrimes( Number.newBuilder().setNumber( number ).build() )
            .forEachRemaining( n -> primeList.add( n.getNumber() ) );
        return new ResponseEntity<>( primeList, HttpStatus.OK );
    }


    @GetMapping ("/clientstream/{number}")
    public ResponseEntity<List<Integer>> clientStreamGrpcCall( @PathVariable ("number") int number )
    {
        final List<Integer> primeList = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch( 1 );
        StreamObserver<Number> numberStreamObserver = stub.primeList( new StreamObserver<NumberList>()
        {
            @Override
            public void onNext( NumberList numberList )
            {
                primeList.addAll( numberList.getNumbersList() );
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

        IntStream.range( 2, number ).boxed()
            .forEach( n -> numberStreamObserver.onNext( Number.newBuilder().setNumber( n ).build() ) );
        numberStreamObserver.onCompleted();
        try {
            latch.await();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( primeList, HttpStatus.OK );
    }

    @GetMapping ("/bidi/{number}")
    public ResponseEntity<List<Integer>> biDiStreamSyncGrpcCall( @PathVariable ("number") int number )
    {
        List<Integer> primeList = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch( 1 );
        StreamObserver<Number> numberStreamObserver = stub.primeStream( new StreamObserver<Number>()
        {
            @Override
            public void onNext( Number number )
            {
                primeList.add( number.getNumber() );
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
        IntStream.range( 2, number ).boxed().forEach( n -> numberStreamObserver.onNext( Number.newBuilder().setNumber( n ).build() ) );
        numberStreamObserver.onCompleted();
        try {
            latch.await();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( primeList, HttpStatus.OK );
    }

}
