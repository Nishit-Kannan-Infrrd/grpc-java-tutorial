package com.crazynerve;

import com.crazynerve.entities.AddressForJson;
import com.crazynerve.entities.PersonForJson;
import com.crazynerve.entities.TestForJson;
import com.crazynerve.proto.Address;
import com.crazynerve.proto.Person;
import com.crazynerve.proto.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.function.Supplier;
import java.util.logging.Logger;


/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger LOGGER = Logger.getLogger( App.class.getName() );
    public static void main( String[] args )
    {
        App app = new App();
        // demo
        // app.demoProtoBuff();
        app.compareJsonVsProto();
    }

    private void demoProtoBuff(){
        com.crazynerve.proto.Address address = com.crazynerve.proto.Address.newBuilder()
            .setStreet1( "Street 1" )
            .setStreet2( "Street 2" )
            .setZip( "123-456" )
            .build();
        com.crazynerve.proto.Person person = com.crazynerve.proto.Person.newBuilder()
            .setName( "NoName" )
            .setAge( 100 )
            .setAddress( address )
            .build();

        LOGGER.info( () -> "Person: " + person );
    }

    private void compareJsonVsProto(){
        // prepare for json - person
        PersonForJson personForJson = new PersonForJson( "NoName", 100, new AddressForJson( "street1", "street2", "123-456" ) );
        ObjectMapper objectMapper = new ObjectMapper();
        Runnable jsonRunnable = () -> {
            try {
                byte[] jsonBytes = objectMapper.writeValueAsBytes( personForJson );
                LOGGER.fine( () -> "Json person byte size " + jsonBytes.length );
                objectMapper.readValue( jsonBytes, PersonForJson.class );
            }  catch ( IOException e ) {
                e.printStackTrace();
            }
        };

        // prepare for proto - person
        Address addressForProto = Address.newBuilder()
            .setStreet1( "street1" )
            .setStreet2( "street2" )
            .setZip( "123-456" )
            .build();
        Person personForProto = Person.newBuilder()
            .setName( "NoName" )
            .setAge( 100 )
            .setAddress( addressForProto )
            .build();
        Runnable protoRunnable = () -> {
            byte[] protoBytes = personForProto.toByteArray();
            LOGGER.fine( () -> "Proto person byte size " + protoBytes.length );
            try {
                Person.parseFrom( protoBytes );
            } catch ( InvalidProtocolBufferException e ) {
                e.printStackTrace();
            }
        };
        LOGGER.info( () -> "Running test for person object." );
        for(int i = 0; i < 10; i++) {
            runTest( protoRunnable, "proto" );
            runTest( jsonRunnable, "json" );
        }

        // prepare for json - test
        TestForJson testForJson = new TestForJson("String", 10);
        Runnable jsonRunnableForTest = () -> {
            try {
                byte[] bytes = objectMapper.writeValueAsBytes( testForJson );
                LOGGER.fine( () -> "Json test byte size " + bytes.length );
                objectMapper.readValue( bytes, TestForJson.class );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        };

        // prepare for json - proto
        Test test = Test.newBuilder()
            .setSField( "String" )
            .setIField( 10 )
            .build();
        Runnable protoRunnableForTest = () -> {
            byte[] bytes = test.toByteArray();
            LOGGER.fine( () -> "Proto test byte size " + bytes.length );
            try {
                Test.parseFrom( bytes );
            } catch ( InvalidProtocolBufferException e ) {
                e.printStackTrace();
            }
        };
        LOGGER.info( () -> "Running test for test object." );
        for(int i = 0; i < 10; i++) {
            runTest( protoRunnableForTest, "proto" );
            runTest( jsonRunnableForTest, "json" );
        }
    }

    private void runTest(Runnable runnable, String type){
        Instant start = Instant.now();
        for(int i=0; i<100000; i++){
            runnable.run();
        }
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between( start, end );
        LOGGER.info( () -> type + " : " + timeElapsed.getNano()/1000000 );
    }
}
