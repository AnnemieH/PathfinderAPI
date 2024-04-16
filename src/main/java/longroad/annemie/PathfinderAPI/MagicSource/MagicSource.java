package com.tsi.longroad.annemie.PathfinderAPI.MagicSource;

import jakarta.persistence.*;

@Entity
@Table ( name = "magic_source" )
public class MagicSource
{
    @Id
    @Column ( name = "magic_source_id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private short magicSourceID;

    @Column ( name = "magic_source_name" )
    private String magicSourceName;

    // GETTERS

    public short getMagicSourceID()
    {
        return magicSourceID;
    }

    public String getMagicSourceName()
    {
        return magicSourceName;
    }
}
