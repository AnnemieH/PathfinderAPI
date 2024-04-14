package com.tsi.longroad.annemie.PathfinderAPI.SpellListSpell;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class SpellListSpellKey implements Serializable
{
    @Column( name = "spell_id")
    private int spellID;

    @Column( name = "spell_list_id" )
    private int spellListID;

    // GETTERS


    public int getSpellID()
    {
        return spellID;
    }

    public int getSpellListID()
    {
        return spellListID;
    }

    // CONSTRUCTORS
    public SpellListSpellKey ()
    {

    }

    public SpellListSpellKey( int spellID, int spellListID )
    {
        this.spellID = spellID;
        this.spellListID = spellListID;
    }

    // OVERRIDDEN METHODS
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        SpellListSpellKey that = (SpellListSpellKey) o;
        return spellID == that.spellID && spellListID == that.spellListID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(spellID, spellListID);
    }
}
