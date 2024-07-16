package longroad.annemie.PathfinderAPI.RaceCreatureSubtype;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.CreatureSubtype.CreatureSubtype;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
import longroad.annemie.PathfinderAPI.Race.Race;

@Entity
@Table ( name = "race_creature_subtype" )
public class RaceCreatureSubtype
{
    @EmbeddedId
    private RaceCreatureSubtypeKey id;

    @ManyToOne
    @MapsId ( "raceID" )
    @JoinColumn ( name = "race_id" )
    @JsonIgnore
    private Race race;

    @ManyToOne
    @MapsId ( "creatureSubtypeID" )
    @JoinColumn ( name = "creature_subtype_id" )
    private CreatureSubtype creatureSubtype;

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
        setMetadata(new Metadata("/raceCreatureSubtypes", true, toString()));
    }

    // GETTERS
    public RaceCreatureSubtypeKey getId()
    {
        return id;
    }

    public Race getRace()
    {
        return race;
    }

    public CreatureSubtype getCreatureSubtype()
    {
        return creatureSubtype;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS

    public void setId(RaceCreatureSubtypeKey id)
    {
        this.id = id;
    }

    public void setRace(Race race)
    {
        this.race = race;
    }

    public void setCreatureSubtype(CreatureSubtype creatureSubtype)
    {
        this.creatureSubtype = creatureSubtype;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods
    @Override
    public String toString()
    {
        return getCreatureSubtype().toString();
    }
}
