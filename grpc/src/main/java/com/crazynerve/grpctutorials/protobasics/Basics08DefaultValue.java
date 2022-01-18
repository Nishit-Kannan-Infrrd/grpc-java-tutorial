package com.crazynerve.grpctutorials.protobasics;

public class Basics08DefaultValue
{
    public static void main( String[] args )
    {
        Course course = Course.newBuilder()
            // .setLecturer( Lecturer.newBuilder().build() )
            .build();
        System.out.println("Lecturer for the course " + course.getLecturer().getName());

        // check if lecturer present
        System.out.println("Is lecturer set : " + course.hasLecturer());
    }
}
