package longroad.annemie.PathfinderAPI.RaceCreatureSubtype;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RaceCreatureSubtypeKey implements Serializable
{
    @Column( name = "race_id" )
    private short raceID;

    @Column( name = "creature_subtype_id" )
    private int creatureSubtypeID;

    // GETTERS

    public short getRaceID()
    {
        return raceID;
    }

    public int getCreatureSubtypeID()
    {
        return creatureSubtypeID;
    }

    // SETTERS

    public void setRaceID(short raceID)
    {
        this.raceID = raceID;
    }

    public void setCreatureSubtypeID(int creatureSubtypeID)
    {
        this.creatureSubtypeID = creatureSubtypeID;
    }

    // CONSTRUCTORS
    public RaceCreatureSubtypeKey ()
    {

    }

    public RaceCreatureSubtypeKey (short raceID, int creatureSubtypeID )
    {
        this.raceID = raceID;
        this.creatureSubtypeID = creatureSubtypeID;
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
        RaceCreatureSubtypeKey that = (RaceCreatureSubtypeKey) o;
        return raceID == that.raceID && creatureSubtypeID == that.creatureSubtypeID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(raceID, creatureSubtypeID);
    }
}
