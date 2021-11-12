package com.crazynerve.primenumber.aggregator;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class AggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregatorApplication.class, args);
	}

	@Bean
	public ManagedChannel managedChannel(){
		return ManagedChannelBuilder.forAddress( "localhost", 5050 ).usePlaintext().build();
	}

}
