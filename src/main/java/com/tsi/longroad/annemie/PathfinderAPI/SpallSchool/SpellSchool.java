package com.tsi.longroad.annemie.PathfinderAPI.SpallSchool;

import jakarta.persistence.*;

@Entity
@Table( name="spell_school" )
public class SpellSchool
{
    @Id
    @Column ( name = "spell_school_id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private short spellSchoolID;

    @Column ( name = "spell_school_name" )
    private String spellSchoolName;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "subschool_of" )
    private SpellSchool subschoolOf;

    // GETTERS

    public short getSpellSchoolID()
    {
        return spellSchoolID;
    }

    public String getSpellSchoolName()
    {
        return spellSchoolName;
    }

    public SpellSchool getSubschoolOf()
    {
        return subschoolOf;
    }
}
