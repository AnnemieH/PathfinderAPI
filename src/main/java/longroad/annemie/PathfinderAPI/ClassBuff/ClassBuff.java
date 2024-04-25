package longroad.annemie.PathfinderAPI.ClassBuff;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import longroad.annemie.PathfinderAPI.Buff.Buff;
import longroad.annemie.PathfinderAPI.CharClass.CharClass;
import jakarta.persistence.*;

@Entity
public class ClassBuff
{
    @EmbeddedId
    private ClassBuffKey id;

    @ManyToOne
    @MapsId("classID")
    @JoinColumn(name = "class_id")
    @JsonBackReference( value = "classBuff" )
    private CharClass currClass;

    @ManyToOne
    @MapsId("buffID")
    @JoinColumn(name = "buff_id")
    private Buff buff;

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

    // SETTERS
    public void setId(ClassBuffKey id)
    {
        this.id = id;
    }

    public void setCurrClass(CharClass currClass)
    {
        this.currClass = currClass;
    }

    public void setBuff(Buff buff)
    {
        this.buff = buff;
    }
}
