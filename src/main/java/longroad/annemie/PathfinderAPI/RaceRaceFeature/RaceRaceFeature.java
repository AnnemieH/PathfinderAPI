package longroad.annemie.PathfinderAPI.RaceRaceFeature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Race.Race;
import longroad.annemie.PathfinderAPI.RaceFeature.RaceFeature;

@Entity
@Table( name = "race_race_feature" )
public class RaceRaceFeature
{
    @EmbeddedId
    private RaceRaceKey id;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "raceID" )
    @JoinColumn ( name = "race_id" )
    @JsonBackReference( value = "raceBuff" )
    private Race race;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "raceFeatureID" )
    @JoinColumn ( name = "race_feature_id" )
    private RaceFeature raceFeature;

    @Column ( name = "trait_slot" )
    private String traitSlot;

    // GETTERS

    public RaceRaceKey getId()
    {
        return id;
    }

    public Race getRace()
    {
        return race;
    }

    public RaceFeature getRaceFeature()
    {
        return raceFeature;
    }

    public String getTraitSlot()
    {
        return traitSlot;
    }

    // SETTERS

    public void setId(RaceRaceKey id)
    {
        this.id = id;
    }

    public void setRace(Race race)
    {
        this.race = race;
    }

    public void setRaceFeature(RaceFeature raceFeature)
    {
        this.raceFeature = raceFeature;
    }

    public void setTraitSlot(String traitSlot)
    {
        this.traitSlot = traitSlot;
    }
}
