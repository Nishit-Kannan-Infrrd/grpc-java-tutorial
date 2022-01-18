package com.crazynerve.grpctutorials.protobasics;

import java.util.Map;


public class Basics06Map
{
    public static void main( String[] args )
    {
        Student studentA = Student.newBuilder().setName( "StudentA" ).setAge( 18 ).build();
        Student studentB = Student.newBuilder().setName( "StudentB" ).setAge( 19 ).build();
        StudentRollNumberMap studentMap = StudentRollNumberMap.newBuilder()
            .putStudentRollMap( 1, studentA )
            .putStudentRollMap( 2, studentB )
            .build();
        System.out.println("Student Map " + studentMap);
        System.out.println("Map " + studentMap.getStudentRollMapMap());
        System.out.println("Student id 1: " + studentMap.getStudentRollMapOrThrow( 1 ));
        System.out.println("Student id 3 (default): " + studentMap.getStudentRollMapOrDefault( 3, Student.newBuilder().setName( "John Doe" ).setAge( 20 ).build() ));
        System.out.println("Student id 3 (error): " + studentMap.getStudentRollMapOrThrow( 3 ));
    }
}
