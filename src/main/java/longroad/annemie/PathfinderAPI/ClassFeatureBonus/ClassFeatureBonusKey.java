package longroad.annemie.PathfinderAPI.ClassFeatureBonus;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ClassFeatureBonusKey
{
    @Column ( name = "class_feature_id" )
    private int classFeatureID;

    @Column ( name = "bonus_id" )
    private int bonusID;

    // GETTERS
    public int getClassFeatureID()
    {
        return classFeatureID;
    }

    public int getBonusID()
    {
        return bonusID;
    }

    // SETTErs
    public void setClassFeatureID(int classFeatureID)
    {
        this.classFeatureID = classFeatureID;
    }

    public void setBonusID(int bonusID)
    {
        this.bonusID = bonusID;
    }

    // CONSTRUCTORS
    public ClassFeatureBonusKey ()
    {

    }
    public ClassFeatureBonusKey ( int classFeatureID, int bonusID )
    {
        this.classFeatureID = classFeatureID;
        this.bonusID = bonusID;
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
        ClassFeatureBonusKey that = (ClassFeatureBonusKey) o;
        return classFeatureID == that.classFeatureID && bonusID == that.bonusID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(classFeatureID, bonusID);
    }
}
