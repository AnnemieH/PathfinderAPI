package longroad.annemie.PathfinderAPI.RaceBuff;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class RaceBuffKey
{
    @Column( name = "race_id" )
    private short raceID;

    @Column( name = "buff_id" )
    private int buffID;

    // GETTERS
    public short getRaceID()
    {
        return raceID;
    }

    public int getBuffID()
    {
        return buffID;
    }

    // SETTERS

    public void setRaceID(short raceID)
    {
        this.raceID = raceID;
    }

    public void setBuffID(int buffID)
    {
        this.buffID = buffID;
    }

    // CONSTRUCTORS
    public RaceBuffKey()
    {

    }
    public RaceBuffKey ( short raceID, int buffID )
    {
        this.raceID = raceID;
        this.buffID = buffID;
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
        RaceBuffKey that = (RaceBuffKey) o;
        return raceID == that.raceID && buffID == that.buffID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(raceID, buffID);
    }
}
