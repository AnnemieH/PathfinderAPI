package longroad.annemie.PathfinderAPI.Race;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.CreatureType.CreatureType;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
import longroad.annemie.PathfinderAPI.RaceRaceFeature.RaceRaceFeature;
import longroad.annemie.PathfinderAPI.RaceCreatureSubtype.RaceCreatureSubtype;
import longroad.annemie.PathfinderAPI.Size.Size;

import java.util.Set;

@Entity
@Table( name = "race" )
public class Race
{
    @Id
    @Column( name = "race_id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private short raceID;

    @Column( name = "race_name" )
    private String raceName;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn( name = "size" )
    private Size size;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn( name = "type" )
    private CreatureType type;

    @OneToMany( mappedBy = "race" )
    @JsonInclude( JsonInclude.Include.NON_EMPTY )
    private Set<RaceCreatureSubtype> subtypes;

    @Column( name = "speed" )
    short speed;

    @Column ( name = "buff_slots" )
    private String buffSlots;

    @OneToMany
    @JoinColumn ( name = "race_id" )
    private Set <RaceRaceFeature> raceFeatures;

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
        setMetadata(new Metadata("/races", true, toString()));
    }

    // GETTERS
    public short getRaceID()
    {
        return raceID;
    }

    public String getRaceName()
    {
        return raceName;
    }

    public Size getSize()
    {
        return size;
    }

    public CreatureType getType()
    {
        return type;
    }

    public Set<RaceCreatureSubtype> getSubtypes()
    {
        return subtypes;
    }

    public short getSpeed()
    {
        return speed;
    }

    public String getBuffSlots()
    {
        return buffSlots;
    }

    public Set<RaceRaceFeature> getRaceFeatures()
    {
        return raceFeatures;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setRaceID(short raceID)
    {
        this.raceID = raceID;
    }

    public void setRaceName(String raceName)
    {
        this.raceName = raceName;
    }

    public void setSize(Size size)
    {
        this.size = size;
    }

    public void setType(CreatureType type)
    {
        this.type = type;
    }

    public void setSubtypes(Set<RaceCreatureSubtype> subtypes)
    {
        this.subtypes = subtypes;
    }

    public void setSpeed(short speed)
    {
        this.speed = speed;
    }

    public void setBuffSlots(String buffSlots)
    {
        this.buffSlots = buffSlots;
    }

    public void setRaceFeatures(Set<RaceRaceFeature> raceFeatures)
    {
        this.raceFeatures = raceFeatures;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods

    @Override
    public String toString()
    {
        String output = getType().getName() + " (";

        for (RaceCreatureSubtype subtype : subtypes)
        {
            // Check to see if this is the first subtype by checking if
            // the last character is a (
            if ( output.substring(output.length() - 1).equals("(") )
            {
                output += subtype.getCreatureSubtype().getName();
            }
            // Otherwise, put a comma between subtypes
            else
            {
                output += ", " + subtype.getCreatureSubtype().getName();
            }
        }

        output += ")";
        return output;
    }
}
