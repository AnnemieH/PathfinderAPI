package com.tsi.longroad.annemie.PathfinderAPI.ClassSkill;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tsi.longroad.annemie.PathfinderAPI.CharClass.CharClass;
import com.tsi.longroad.annemie.PathfinderAPI.ClassBuff.ClassBuffKey;
import com.tsi.longroad.annemie.PathfinderAPI.Skill.Skill;
import jakarta.persistence.*;

@Entity
public class ClassSkill
{
    @EmbeddedId
    private ClassSkillKey id;

    @ManyToOne
    @MapsId("classID")
    @JoinColumn ( name = "char_class_id" )
    @JsonBackReference
    private CharClass charClass;

    @ManyToOne
    @MapsId("skillID")
    @JoinColumn ( name = "skill_id")
    @JsonBackReference
    private Skill skill;

    // GETTERS

    public ClassSkillKey getId()
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
