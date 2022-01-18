package com.crazynerve.grpctutorials.protobasics;

public class Basics07Enum
{
    public static void main( String[] args )
    {
        Principal principalA = Principal.newBuilder().setName( "PrincipalA" ).build();
        Principal principalB = Principal.newBuilder().setName( "PrincipalB" ).setGender( Gender.MALE ).build();
        Principal principalC = Principal.newBuilder().setName( "PrincipalC" ).setGender( Gender.FEMALE ).build();

        System.out.println("Principal A " + principalA + " Gender : " + principalA.getGender());
        System.out.println("Principal B " + principalB + " Gender : " + principalB.getGender());
        System.out.println("Principal C " + principalC + " Gender : " + principalC.getGender());
    }
}
