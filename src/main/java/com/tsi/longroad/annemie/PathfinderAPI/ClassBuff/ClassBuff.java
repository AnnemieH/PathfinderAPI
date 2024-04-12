package com.tsi.longroad.annemie.PathfinderAPI.ClassBuff;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tsi.longroad.annemie.PathfinderAPI.Buff.Buff;
import com.tsi.longroad.annemie.PathfinderAPI.CharClass.CharClass;
import jakarta.persistence.*;

@Entity
public class ClassBuff
{
    @EmbeddedId
    private ClassBuffKey id;

    @ManyToOne
    @MapsId("classID")
    @JoinColumn(name = "class_id")
    @JsonBackReference
    private CharClass currClass;

    @ManyToOne
    @MapsId("buffID")
    @JoinColumn(name = "buff_id")
    private Buff buff;

//    @ManyToOne
//    @MapsId("level")
//    @JoinColumn(name = "level")
    //short level;

    // GETTERS

    public ClassBuffKey getId()
    {
        return id;
    }

    public CharClass getCurrClass()
    {
        return currClass;
    }

    public Buff getBuff()
    {
        return buff;
    }
}
