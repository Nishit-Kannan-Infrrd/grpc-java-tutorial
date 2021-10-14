package com.crazynerve.entities;

public class PersonForJson
{
    private String name;
    private int age;
    private AddressForJson address;


    public PersonForJson()
    {
    }


    public PersonForJson( String name, int age, AddressForJson address )
    {
        this.name = name;
        this.age = age;
        this.address = address;
    }


    public String getName()
    {
        return name;
    }


    public void setName( String name )
    {
        this.name = name;
    }


    public int getAge()
    {
        return age;
    }


    public void setAge( int age )
    {
        this.age = age;
    }


    public AddressForJson getAddress()
    {
        return address;
    }


    public void setAddress( AddressForJson address )
    {
        this.address = address;
    }
}
