package com.tsi.longroad.annemie.PathfinderAPI.SpellcasterType;

import jakarta.persistence.*;

@Entity
@Table( name="spellcaster_type" )
public class SpellcasterType
{
    @Id
    @Column ( name="spellcaster_type_id", unique = true )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short spellcasterTypeId;

    @Column ( name="name")
    private String spellcasterTypeName;

    // GETTERS

    public short getSpellcasterTypeId()
    {
        return spellcasterTypeId;
    }

    public String getSpellcasterTypeName()
    {
        return spellcasterTypeName;
    }
}
