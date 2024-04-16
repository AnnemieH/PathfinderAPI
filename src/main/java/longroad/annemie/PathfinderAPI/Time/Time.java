package com.tsi.longroad.annemie.PathfinderAPI.Time;

import jakarta.persistence.*;

@Entity
@Table( name = "time" )
public class Time
{
    @Id
    @Column( name = "time_id", unique = true )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private short timeID;

    @Column( name = "time_name" )
    private String timeName;

    // GETTERS

    public short getTimeID()
    {
        return timeID;
    }

    public String getTimeName()
    {
        return timeName;
    }
}
