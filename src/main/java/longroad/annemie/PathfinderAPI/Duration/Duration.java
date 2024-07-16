package longroad.annemie.PathfinderAPI.Duration;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Attribute.Attribute;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
import longroad.annemie.PathfinderAPI.Time.Time;

@Entity
@Table( name="duration" )
public class Duration
{
    @Id
    @Column( name = "duration_id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int durationID;

    @ManyToOne
    @JoinColumn( name = "unit" )
    private Time unit;

    @Column ( name = "constant" )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Short constant;

    @Column ( name = "level" )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Short level;

    @ManyToOne
    @JoinColumn ( name = "attribute_modifier" )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Attribute attributeModifier;

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
        setMetadata(new Metadata("/durations", true, toString()));
    }

    // GETTERS

    public int getDurationID()
    {
        return durationID;
    }

    public Time getUnit()
    {
        return unit;
    }

    public Short getConstant()
    {
        return constant;
    }

    public Short getLevel()
    {
        return level;
    }

    public Attribute getAttributeModifier()
    {
        return attributeModifier;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setDurationID(int durationID)
    {
        this.durationID = durationID;
    }

    public void setUnit(Time unit)
    {
        this.unit = unit;
    }

    public void setConstant(Short constant)
    {
        this.constant = constant;
    }

    public void setLevel(Short level)
    {
        this.level = level;
    }

    public void setAttributeModifier(Attribute attributeModifier)
    {
        this.attributeModifier = attributeModifier;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods

    @Override
    public String toString()
    {
        String output = "";

        if ( getConstant() != null )
        {
            output += getConstant();
        }

        if ( getAttributeModifier() != null )
        {
            output += " + " + getAttributeModifier().getShortName();
        }

        if ( getLevel() != null )
        {
            output += " + " + getLevel() + "/level";
        }

        output += " " + getUnit().getTimeName() + "(s)";

        return output;
    }
}
