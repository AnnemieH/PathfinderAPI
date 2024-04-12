package com.tsi.longroad.annemie.PathfinderAPI.CharClassSkill;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tsi.longroad.annemie.PathfinderAPI.CharClass.CharClass;
import com.tsi.longroad.annemie.PathfinderAPI.Skill.Skill;
import jakarta.persistence.*;

@Entity
public class CharClassSkill
{
    @EmbeddedId
    private CharClassSkillKey id;

    @ManyToOne
    @MapsId("classID")
    @JoinColumn ( name = "char_class_id" )
    @JsonBackReference
    private CharClass charClass;

    @ManyToOne
    @MapsId("skillID")
    @JoinColumn ( name = "skill_id")
    private Skill skill;

    // GETTERS

    public CharClassSkillKey getId()
    {
        return id;
    }

    public CharClass getCharClass()
    {
        return charClass;
    }

    public Skill getSkill()
    {
        return skill;
    }
}
