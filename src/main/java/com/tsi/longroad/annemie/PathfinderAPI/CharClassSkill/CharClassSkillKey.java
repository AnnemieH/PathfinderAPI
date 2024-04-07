package com.tsi.longroad.annemie.PathfinderAPI.ClassSkill;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class ClassSkillKey implements Serializable
{
    @Column(name = "class_id")
    private int classID;

    @Column(name = "skill_id")
    private short skillID;

    // CONSTRUCTORS
    public ClassSkillKey()
    {

    }

    public ClassSkillKey(int classID, short skillID)
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
        ClassSkillKey that = (ClassSkillKey) o;
        return classID == that.classID && skillID == that.skillID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(classID, skillID);
    }
}
