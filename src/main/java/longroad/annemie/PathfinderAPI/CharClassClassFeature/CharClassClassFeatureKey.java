package longroad.annemie.PathfinderAPI.CharClassClassFeature;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CharClassClassFeatureKey implements Serializable
{
    @Column( name = "class_id")
    private int classID;

    @Column( name = "class_feature_id")
    private int classFeatureID;

    @Column( name = "level")
    private short level;


    // GETTERS
    public int getClassID()
    {
        return classID;
    }

    public int getClassFeatureID()
    {
        return classFeatureID;
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

    public void setClassFeatureID(int classFeatureID)
    {
        this.classFeatureID = classFeatureID;
    }

    public void setLevel(short level)
    {
        this.level = level;
    }

    // CONSTRUCTORS
    public CharClassClassFeatureKey()
    {

    }
    public CharClassClassFeatureKey(int classID, int classFeatureID, short level)
    {
        this.classID = classID;
        this.classFeatureID = classFeatureID;
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
        CharClassClassFeatureKey that = (CharClassClassFeatureKey) o;
        return classID == that.classID && classFeatureID == that.classFeatureID && level == that.level;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(classID, classFeatureID, level);
    }

}
