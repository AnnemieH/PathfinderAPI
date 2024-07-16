package longroad.annemie.PathfinderAPI.BonusType;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table( name = "bonus_type" )
public class BonusType
{
    @Id
    @Column ( name = "bonus_type_id" )
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private short bonusTypeID;

    @Column ( name = "name" )
    private String name;

    @Column ( name = "stackable" )
    private boolean stackable;

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
        setMetadata(new Metadata("/bonusTypes", false, toString()));
    }

    // GETTERS
    public short getBonusTypeID()
    {
        return bonusTypeID;
    }

    public String getName()
    {
        return name;
    }

    public boolean isStackable()
    {
        return stackable;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS

    public void setBonusTypeID(short bonusTypeID)
    {
        this.bonusTypeID = bonusTypeID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStackable(boolean stackable)
    {
        this.stackable = stackable;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overwritten methods
    @Override
    public String toString()
    {
        return name;
    }
}
