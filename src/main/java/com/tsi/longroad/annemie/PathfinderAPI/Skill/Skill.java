package com.tsi.longroad.annemie.PathfinderAPI.Skill;

import com.tsi.longroad.annemie.PathfinderAPI.Attribute.Attribute;
import jakarta.persistence.*;

@Entity
@Table(name="skill")
public class Skill
{
    @Id
    @Column (name="skill_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short skillID;

    @Column (name="skill_name")
    private String skillName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="attribute")
    private Attribute attribute;

    @Column (name="trained_only")
    private boolean trainedOnly;

    // GETTERS

    public short getSkillID()
    {
        return skillID;
    }

    public String getSkillName()
    {
        return skillName;
    }

    public Attribute getAttribute()
    {
        return attribute;
    }

    public boolean getTrainedOnly()
    {
        return trainedOnly;
    }
}
