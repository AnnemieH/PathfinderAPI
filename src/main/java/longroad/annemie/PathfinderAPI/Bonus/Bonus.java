package longroad.annemie.PathfinderAPI.Bonus;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.BonusTarget.BonusTarget;
import longroad.annemie.PathfinderAPI.BonusType.BonusType;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

import java.lang.annotation.Target;

@Entity
@Table ( name = "bonus" )
public class Bonus
{
    @Id
    @Column ( name = "bonus_id" )
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private int bonusID;

    @Column ( name = "value" )
    private short value;

    @ManyToOne
    @JoinColumn ( name = "target" )
    private BonusTarget target;

    @ManyToOne
    @JoinColumn ( name = "type" )
    private BonusType type;

    @Transient
    private Metadata metadata;

    // Initialisation
    @PostLoad
    public void init()
    {
        metadataInit();
    }

    // Make sure metadata is initialised properly
    public void metadataInit()
    {
        setMetadata(new Metadata("/bonuses", true, toString()));
    }

    // GETTERS
    public int getBonusID()
    {
        return bonusID;
    }

    public short getValue()
    {
        return value;
    }

    public BonusTarget getTarget()
    {
        return target;
    }

    public BonusType getType()
    {
        return type;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTErS
    public void setBonusID(int bonusID)
    {
        this.bonusID = bonusID;
    }

    public void setValue(short value)
    {
        this.value = value;
    }

    public void setTarget(BonusTarget target)
    {
        this.target = target;
    }

    public void setType(BonusType type)
    {
        this.type = type;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods

    @Override
    public String toString()
    {
        String signedValue;
        if ( value >= 0 )
        {
            signedValue = "+" + value;
        }
        else
        {
            signedValue = "-" + value;
        }

        return signedValue + " " + type + " bonus to " +
               target;
    }
}
