package com.crazynerve.grpctutorials.protobasics;

public class Basics09OneOf
{
    public static void main( String[] args )
    {
        EmailDetails emailDetails = EmailDetails.newBuilder().setEmailAddress( "a@b.com" ).build();
        PhoneDetails phoneDetails = PhoneDetails.newBuilder().setPhoneNumber( 123456 ).setCountryCode( 91 ).build();

        Communication communicationA = Communication.newBuilder().setIdentifier( "A" ).setEmailMode( emailDetails ).build();
        Communication communicationB = Communication.newBuilder().setIdentifier( "B" ).setPhoneMode( phoneDetails ).build();
        Communication communicationC = Communication.newBuilder().setIdentifier( "C" ).setEmailMode( emailDetails ).setPhoneMode( phoneDetails ).build();
        communicationDetails( communicationA );
        communicationDetails( communicationB );
        communicationDetails( communicationC );
        communicationDetailsCase( communicationA );
        communicationDetailsCase( communicationB );
        communicationDetailsCase( communicationC );
    }

    private static void communicationDetails(Communication communication){
        System.out.println("Identifier : " + communication.getIdentifier() + " emails Details " + communication.getEmailMode() + " phone details " + communication.getPhoneMode());
    }

    private static void communicationDetailsCase(Communication communication){
        switch ( communication.getCommunicationModeCase() ){
            case EMAIL_MODE:
                System.out.println("Identifier " + communication.getIdentifier() + " Email Mode: " + communication.getEmailMode());
                break;
            case PHONE_MODE:
                System.out.println("Identifier " + communication.getIdentifier() + " Phone Mode: " + communication.getPhoneMode());
                break;
        }
    }
}
