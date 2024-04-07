package com.tsi.longroad.annemie.PathfinderAPI.Class;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tsi.longroad.annemie.PathfinderAPI.Buff.Buff;
import com.tsi.longroad.annemie.PathfinderAPI.ClassBuff.ClassBuff;
import jakarta.persistence.*;

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

    //@JsonIgnore
    @Column (name="BAB")
    private String bab;

    @Column (name="fortitude")
    private String fortitude;

    @Column (name = "reflex")
    private String reflex;

    @Column (name = "will")
    private String will;

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

    public Set<ClassBuff> getBuffs()
    {
        return buffs;
    }
}
