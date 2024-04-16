package com.tsi.longroad.annemie.PathfinderAPI.SpellList;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tsi.longroad.annemie.PathfinderAPI.SpellListSpell.SpellListSpell;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table ( name = "spell_list" )
public class SpellList
{
    @Id
    @Column ( name = "spell_list_id", unique = true )
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private short spellListID;

    @Column ( name = "spell_list_name" )
    private String spellListName;

    @OneToMany ( mappedBy = "spellList" )
    @JsonManagedReference
    private List<SpellListSpell> Spells = new ArrayList<>();

    // GETTERS

    public short getSpellListID()
    {
        return spellListID;
    }

    public String getSpellListName()
    {
        return spellListName;
    }

    public List<SpellListSpell> getSpells()
    {
        return Spells;
    }
}
