package com.crazynerve.grpctutorials.protobasics;

import java.util.ArrayList;
import java.util.List;


public class Basics05Collection
{
    public static void main( String[] args )
    {
        Lecturer lecturerA = Lecturer.newBuilder().setName( "LecA" ).build();
        Lecturer lecturerB = Lecturer.newBuilder().setName( "LecA" ).build();
        Lecturer lecturerC = Lecturer.newBuilder().setName( "LecA" ).build();

        List<Course> courses = new ArrayList<>();
        courses.add(Course.newBuilder().setCourseName( "CourseA" ).setLecturer( lecturerA ).build());
        courses.add(Course.newBuilder().setCourseName( "CourseB" ).setLecturer( lecturerB ).build());
        courses.add(Course.newBuilder().setCourseName( "CourseC" ).setLecturer( lecturerC ).build());

        Student student = Student.newBuilder().setName( "StudentA" ).setAge( 18 ).addAllCourses( courses ).build();

        System.out.println("Student " + student);

    }
}
