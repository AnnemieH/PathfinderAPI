package longroad.annemie.PathfinderAPI.Duration;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
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

    @Column ( name = "attribute_modifier" )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Short attributeModifier;

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

    public Short getAttributeModifier()
    {
        return attributeModifier;
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

    public void setAttributeModifier(Short attributeModifier)
    {
        this.attributeModifier = attributeModifier;
    }
}
