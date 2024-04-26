package longroad.annemie.PathfinderAPI.RaceBuff;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Buff.Buff;
import longroad.annemie.PathfinderAPI.Race.Race;

@Entity
@Table( name = "race_buff" )
public class RaceBuff
{
    @EmbeddedId
    private RaceBuffKey id;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "raceID" )
    @JoinColumn ( name = "race_id" )
    @JsonBackReference( value = "raceBuff" )
    private Race race;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "buffID" )
    @JoinColumn ( name = "buff_id" )
    private Buff buff;

    @Column ( name = "trait_slot" )
    private String traitSlot;

    // GETTERS

    public RaceBuffKey getId()
    {
        return id;
    }

    public Race getRace()
    {
        return race;
    }

    public Buff getBuff()
    {
        return buff;
    }

    public String getTraitSlot()
    {
        return traitSlot;
    }

    // SETTERS

    public void setId(RaceBuffKey id)
    {
        this.id = id;
    }

    public void setRace(Race race)
    {
        this.race = race;
    }

    public void setBuff(Buff buff)
    {
        this.buff = buff;
    }

    public void setTraitSlot(String traitSlot)
    {
        this.traitSlot = traitSlot;
    }
}
