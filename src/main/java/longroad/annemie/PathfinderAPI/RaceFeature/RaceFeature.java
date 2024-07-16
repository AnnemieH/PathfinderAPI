package longroad.annemie.PathfinderAPI.RaceFeature;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.BuffType.BuffType;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
import longroad.annemie.PathfinderAPI.RaceRaceFeature.RaceRaceFeature;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="race_feature")
public class RaceFeature
{
    @Id
    @Column ( name = "race_feature_id", unique = true)
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int raceFeatureID;

    @Column ( name = "name" )
    private String name;

    @Column ( name = "description" )
    @JsonIgnore
    private String descriptionRaw;

    @Transient
    private String[] description;

    @OneToMany(mappedBy = "raceFeature")
    @JsonBackReference( value="buffRaces" )
    private Set<RaceRaceFeature> Races = new HashSet<>();

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "buff_type_id")
    private BuffType buffType;

    @Transient
    private Metadata metadata;

    // Initialisation
    @PostLoad
    private void init()
    {
        buffDescInit();
        metadataInit();
    }

    // Ensure metadata is initiälised properly
    private void metadataInit()
    {
        setMetadata(new Metadata("/raceFeatures", true, toString()));
    }

    // Ensure that buffDesc is initiälised correctly
    public void buffDescInit()
    {
        // If descriptionRaw is non-empty, split it at newline characters
        if (descriptionRaw != null )
        {
            description = descriptionRaw.split("\\\\n");
        }
    }

    // GETTERS
    public int getRaceFeatureID()
    {
        return raceFeatureID;
    }

    public String getName()
    {
        return name;
    }

    public String getDescriptionRaw()
    {
        return descriptionRaw;
    }

    public String[] getDescription()
    {
        return description;
    }

    public BuffType getBuffType()
    {
        return buffType;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setRaceFeatureID(int raceFeatureID)
    {
        this.raceFeatureID = raceFeatureID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescriptionRaw(String descriptionRaw)
    {
        this.descriptionRaw = descriptionRaw;
    }

    public void setBuffDescriptionRaw(String[] buffDesc)
    {
        String raw = "";
        for ( String line : buffDesc )
        {
            raw += line;
        }

        if (raw != descriptionRaw)
        {
            descriptionRaw = raw;
        }
    }

    public void setDescription(String[] description)
    {
        this.description = description;
        // If buffDesc differs from buffDescriptionRaw, set them equivalent
        String raw = "";
        for ( String line : description)
        {
            raw += line + "\r\n";
        }

        if (raw != descriptionRaw)
        {
            descriptionRaw = raw;
        }
    }

    public void setBuffType(BuffType buffType)
    {
        this.buffType = buffType;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods

    @Override
    public String toString()
    {
        return getName();
    }
}
