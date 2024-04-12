package com.tsi.longroad.annemie.PathfinderAPI.Attribute;

import jakarta.persistence.*;

@Entity
@Table (name="attribute")
public class Attribute
{
    @Id
    @Column ( name = "attribute_id", unique = true )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short attributeID;

    @Column ( name = "attribute_name")
    private String attributeName;

    @Column ( name = "short_name")
    private String shortName;

    // GETTERS

    public short getAttributeID()
    {
        return attributeID;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public String getShortName()
    {
        return shortName;
    }
}
