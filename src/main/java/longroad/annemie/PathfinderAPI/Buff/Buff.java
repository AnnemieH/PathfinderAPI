package com.tsi.longroad.annemie.PathfinderAPI.Buff;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tsi.longroad.annemie.PathfinderAPI.BuffType.BuffType;
import com.tsi.longroad.annemie.PathfinderAPI.ClassBuff.ClassBuff;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="buff")
public class Buff
{
    @Id
    @Column ( name = "buff_id", unique = true)
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int buffID;

    @Column ( name = "buff_name" )
    private String buffName;

    @Column ( name = "buff_description" )
    @JsonIgnore
    private String buffDescriptionRaw;

    @Transient
    private String[] buffDesc;

    @OneToMany(mappedBy = "buff")
    @JsonManagedReference
    @JsonIgnore
    private Set<ClassBuff> classes = new HashSet<>();

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "buff_type_id")
    private BuffType buffType;

    // Ensure that buffDesc is set correctly
    @PostLoad
    public void init()
    {
        // If buffDescriptionRaw is non-empty, split it at newline characters
        if ( buffDescriptionRaw != null )
        {
            buffDesc = buffDescriptionRaw.split("\\\\n");
        }
    }

    @PostUpdate
    public void check()
    {
        System.out.println(buffDescriptionRaw);
    }


    // GETTERS

    public int getBuffID()
    {
        return buffID;
    }

    public String getBuffName()
    {
        return buffName;
    }

    public String getBuffDescriptionRaw()
    {
        return buffDescriptionRaw;
    }

    public String[] getBuffDesc()
    {
        return buffDesc;
    }

    public BuffType getBuffType()
    {
        return buffType;
    }

    // SETTERS

    public void setBuffID(int buffID)
    {
        this.buffID = buffID;
    }

    public void setBuffName(String buffName)
    {
        this.buffName = buffName;
    }

    public void setBuffDescriptionRaw(String buffDescriptionRaw)
    {
        this.buffDescriptionRaw = buffDescriptionRaw;
    }

    public void setBuffDescriptionRaw(String[] buffDesc)
    {
        String raw = "";
        for ( String line : buffDesc )
        {
            raw += line;
        }

        if ( raw != buffDescriptionRaw )
        {
            buffDescriptionRaw = raw;
        }
    }

    public void setBuffDesc(String[] buffDesc)
    {
        this.buffDesc = buffDesc;
        // If buffDesc differs from buffDescriptionRaw, set them equivalent
        String raw = "";
        for ( String line : buffDesc )
        {
            raw += line + "\r\n";
        }

        if ( raw != buffDescriptionRaw )
        {
            buffDescriptionRaw = raw;
        }
        System.out.println(getBuffDescriptionRaw());
    }

    public void setBuffType(BuffType buffType)
    {
        this.buffType = buffType;
    }
}
