package longroad.annemie.PathfinderAPI.RaceRaceFeature;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class RaceRaceFeatureKey
{
    @Column( name = "race_id" )
    private short raceID;

    @Column( name = "race_feature_id" )
    private int raceFeatureID;

    // GETTERS
    public short getRaceID()
    {
        return raceID;
    }

    public int getRaceFeatureID()
    {
        return raceFeatureID;
    }

    // SETTERS

    public void setRaceID(short raceID)
    {
        this.raceID = raceID;
    }

    public void setRaceFeatureID(int raceFeatureID)
    {
        this.raceFeatureID = raceFeatureID;
    }

    // CONSTRUCTORS
    public RaceRaceFeatureKey()
    {

    }
    public RaceRaceFeatureKey(short raceID, int raceFeatureID)
    {
        this.raceID = raceID;
        this.raceFeatureID = raceFeatureID;
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
        RaceRaceFeatureKey that = (RaceRaceFeatureKey) o;
        return raceID == that.raceID && raceFeatureID == that.raceFeatureID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(raceID, raceFeatureID);
    }
}
