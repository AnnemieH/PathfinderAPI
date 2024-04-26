package longroad.annemie.PathfinderAPI.ClassBuff;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClassBuffKey implements Serializable
{
    @Column( name = "class_id")
    private int classID;

    @Column( name = "buff_id")
    private int buffID;

    @Column( name = "level")
    private short level;


    // GETTERS

    public int getClassID()
    {
        return classID;
    }

    public int getBuffID()
    {
        return buffID;
    }

    public short getLevel()
    {
        return level;
    }

    // SETTERS

    public void setClassID(int classID)
    {
        this.classID = classID;
    }

    public void setBuffID(int buffID)
    {
        this.buffID = buffID;
    }

    public void setLevel(short level)
    {
        this.level = level;
    }

    // CONSTRUCTORS
    public ClassBuffKey()
    {

    }
    public ClassBuffKey ( int classID, int buffID, short level )
    {
        this.classID = classID;
        this.buffID = buffID;
        this.level = level;
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
        ClassBuffKey that = (ClassBuffKey) o;
        return classID == that.classID && buffID == that.buffID && level == that.level;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(classID, buffID, level);
    }

}
