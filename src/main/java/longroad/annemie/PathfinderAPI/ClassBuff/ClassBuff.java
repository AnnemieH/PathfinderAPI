package longroad.annemie.PathfinderAPI.ClassBuff;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import longroad.annemie.PathfinderAPI.Buff.Buff;
import longroad.annemie.PathfinderAPI.CharClass.CharClass;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table ( name = "class_buff" )
public class ClassBuff
{
    @EmbeddedId
    private ClassBuffKey id;

    @ManyToOne
    @MapsId ( "classID" )
    @JoinColumn ( name = "class_id" )
    @JsonBackReference( value = "classBuff" )
    private CharClass currClass;

    @ManyToOne
    @MapsId ( "buffID" )
    @JoinColumn ( name = "buff_id" )
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

    // CONSTRUCTORS
    public ClassBuff ()
    {

    }

    public ClassBuff ( ClassBuffKey id, CharClass currClass, Buff buff, short level )
    {
        this.id = id;
        this.currClass = currClass;
        this.buff = buff;
    }

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
        ClassBuff classBuff = (ClassBuff) o;
        return Objects.equals(id, classBuff.id) && Objects.equals(currClass, classBuff.currClass) && Objects.equals(buff, classBuff.buff);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, currClass, buff);
    }
}
