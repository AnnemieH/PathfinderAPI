package longroad.annemie.PathfinderAPI.RaceRaceFeature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
import longroad.annemie.PathfinderAPI.Race.Race;
import longroad.annemie.PathfinderAPI.RaceFeature.RaceFeature;

@Entity
@Table( name = "race_race_feature" )
public class RaceRaceFeature
{
    @EmbeddedId
    private RaceRaceFeatureKey id;

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

    @Transient
    private Metadata metadata;

    // Initialisation
    @PostLoad
    private void init()
    {
        metadataInit();
    }

    // Ensure metadata is initiälised properly
    private void metadataInit()
    {
        setMetadata(new Metadata("/raceRaceFeatures", true, toString()));
    }

    // GETTERS
    public RaceRaceFeatureKey getId()
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

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setId(RaceRaceFeatureKey id)
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

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods
    @Override
    public String toString()
    {
        return getRaceFeature().getName() + " is a feature of " +
               getRace().getRaceName() + " occupying slot " +
               getTraitSlot();
    }
}
