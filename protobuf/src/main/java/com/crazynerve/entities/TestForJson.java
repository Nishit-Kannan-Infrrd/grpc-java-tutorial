package com.crazynerve.entities;

public class TestForJson
{
    private String sField;
    private int iField;


    public TestForJson()
    {
    }


    public TestForJson( String sField, int iField )
    {
        this.sField = sField;
        this.iField = iField;
    }


    public String getsField()
    {
        return sField;
    }


    public void setsField( String sField )
    {
        this.sField = sField;
    }


    public int getiField()
    {
        return iField;
    }


    public void setiField( int iField )
    {
        this.iField = iField;
    }
}
