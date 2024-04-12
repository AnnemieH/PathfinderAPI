package com.tsi.longroad.annemie.PathfinderAPI.CharClassSkill;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class CharClassSkillKey implements Serializable
{
    @Column(name = "class_id")
    private int classID;

    @Column(name = "skill_id")
    private short skillID;

    // CONSTRUCTORS
    public CharClassSkillKey()
    {

    }

    public CharClassSkillKey(int classID, short skillID)
    {
        this.classID = classID;
        this.skillID = skillID;
    }

    // GETTERS

    public int getClassID()
    {
        return classID;
    }

    public short getSkillID()
    {
        return skillID;
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
        CharClassSkillKey that = (CharClassSkillKey) o;
        return classID == that.classID && skillID == that.skillID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(classID, skillID);
    }
}
