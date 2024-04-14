package com.tsi.longroad.annemie.PathfinderAPI.SpellListSpell;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tsi.longroad.annemie.PathfinderAPI.Spell.Spell;
import com.tsi.longroad.annemie.PathfinderAPI.SpellList.SpellList;
import jakarta.persistence.*;

@Entity
@Table ( name = "spell_list_spell" )
public class SpellListSpell
{
    @EmbeddedId
    private SpellListSpellKey id;

    @ManyToOne
    @MapsId( "spellID" )
    @JoinColumn ( name = "spell_id" )
    @JsonBackReference
    private Spell spell;

    @ManyToOne
    @MapsId ( "spellListID" )
    @JoinColumn ( name = "spell_list_id" )
    @JsonBackReference
    private SpellList spellList;

    @Column ( name = "level" )
    private short level;

    // GETTERS

    public SpellListSpellKey getId()
    {
        return id;
    }

    public Spell getSpell()
    {
        return spell;
    }

    public SpellList getSpellList()
    {
        return spellList;
    }

    public short getLevel()
    {
        return level;
    }
}
