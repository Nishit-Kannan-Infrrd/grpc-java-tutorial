package com.crazynerve.entities;

public class AddressForJson
{
    private String street1;
    private String street2;
    private String zip;


    public AddressForJson()
    {
    }


    public AddressForJson( String street1, String street2, String zip )
    {
        this.street1 = street1;
        this.street2 = street2;
        this.zip = zip;
    }


    public String getStreet1()
    {
        return street1;
    }


    public void setStreet1( String street1 )
    {
        this.street1 = street1;
    }


    public String getStreet2()
    {
        return street2;
    }


    public void setStreet2( String street2 )
    {
        this.street2 = street2;
    }


    public String getZip()
    {
        return zip;
    }


    public void setZip( String zip )
    {
        this.zip = zip;
    }
}
