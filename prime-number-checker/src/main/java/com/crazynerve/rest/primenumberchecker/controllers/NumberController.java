package com.crazynerve.rest.primenumberchecker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;


@RestController
@RequestMapping ("/rest")
public class NumberController
{
    @GetMapping("/prime/{number}")
    public ResponseEntity<Boolean> findIfPrime( @PathVariable ("number") int number )
    {
        return new ResponseEntity<>( isPrime( number ), HttpStatus.OK );
    }


    private boolean isPrime( int number )
    {
        return number > 1 && IntStream.range( 2, number ).noneMatch( index -> number % index == 0 );
    }
}
