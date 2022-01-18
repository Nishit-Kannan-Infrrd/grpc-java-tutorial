package com.crazynerve.grpctutorials.protobasics;

public class Basics04Composition
{
    public static void main( String[] args )
    {
        Lecturer lecturer = Lecturer.newBuilder().setName( "Professor Doe" ).build();
        Course course = Course.newBuilder().setCourseName( "Mathematics" ).setLecturer( lecturer ).build();

        System.out.println("Course " + course);
    }
}
