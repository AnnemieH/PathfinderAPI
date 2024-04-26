package longroad.annemie.PathfinderAPI.RaceCreatureSubtype;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.CreatureSubtype.CreatureSubtype;
import longroad.annemie.PathfinderAPI.Race.Race;

@Entity
@Table ( name = "race_creature_subtype" )
public class RaceCreatureSubtype
{
    @EmbeddedId
    private RaceCreatureSubtypeKey id;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "raceID" )
    @JoinColumn ( name = "race_id" )
    @JsonIgnore
    private Race race;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "creatureSubtypeID" )
    @JoinColumn ( name = "creature_subtype_id" )
    private CreatureSubtype creatureSubtype;

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
}
