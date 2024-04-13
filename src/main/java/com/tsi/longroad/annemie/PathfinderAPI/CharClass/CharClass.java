package com.tsi.longroad.annemie.PathfinderAPI.CharClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tsi.longroad.annemie.PathfinderAPI.ClassBuff.ClassBuff;
import com.tsi.longroad.annemie.PathfinderAPI.CharClassSkill.CharClassSkill;
import com.tsi.longroad.annemie.PathfinderAPI.SpellcasterType.SpellcasterType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="char_class")
public class CharClass
{
    @Id
    @Column (name="class_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classID;

    @Column (name = "class_name")
    private String className;


    @Column (name="archetype_of")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer archetypeID;

    @OneToMany ( mappedBy = "currClass")
    @JsonManagedReference
    private Set<ClassBuff> buffs;

    @Column (name="hit_die")
    private short hitDie;
    @Column (name="BAB")
    private String bab;

    @Column (name="fortitude")
    private String fortitude;

    @Column (name = "reflex")
    private String reflex;

    @Column (name = "will")
    private String will;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn( name = "spellcaster_type" )
    private SpellcasterType spellcasterType;

    @Column ( name = "skill_ranks")
    private short skillRanks;

    @OneToMany
    @JoinColumn ( name = "char_class_id" )
    private Set<CharClassSkill> classSkills = new HashSet<>();


    // GETTERS

    public int getClassID()
    {
        return classID;
    }

    public String getClassName()
    {
        return className;
    }

    public Integer getArchetypeID()
    {
        return archetypeID;
    }

    public short getHitDie()
    {
        return hitDie;
    }

    public String getBab()
    {
        return bab;
    }

    public String getFortitude()
    {
        return fortitude;
    }

    public String getReflex()
    {
        return reflex;
    }

    public String getWill()
    {
        return will;
    }

    public SpellcasterType getSpellcasterType()
    {
        return spellcasterType;
    }

    public Set<ClassBuff> getBuffs()
    {
        return buffs;
    }

    public short getSkillRanks()
    {
        return skillRanks;
    }

    public Set<CharClassSkill> getClassSkills()
    {
        return classSkills;
    }
}
