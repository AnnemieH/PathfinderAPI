package longroad.annemie.PathfinderAPI.BonusTarget;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table ( name = "bonus_target" )
public class BonusTarget
{
    @Id
    @Column ( name = "bonus_target_id", unique = true )
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private short bonusTargetID;

    @Column ( name = "name" )
    private String name;

    @Transient
    private Metadata metadata;

    // Initialisation
    @PostLoad
    private void init()
    {
        metadataInit();
    }

    private void metadataInit()
    {
        setMetadata(new Metadata("/bonusTargets", false, toString()));
    }

    // GETTERS
    public short getBonusTargetID()
    {
        return bonusTargetID;
    }

    public String getName()
    {
        return name;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setBonusTargetID(short bonusTargetID)
    {
        this.bonusTargetID = bonusTargetID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // OVERRIDDEN METHODS
    @Override
    public String toString()
    {
        return name;
    }
}
