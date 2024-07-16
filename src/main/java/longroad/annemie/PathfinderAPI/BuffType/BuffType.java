package longroad.annemie.PathfinderAPI.BuffType;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table ( name="buff_type")
public class BuffType
{
    @Id
    @Column( name = "type_id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int buffTypeID;

    @Column ( name = "type_name")
    private String typeName;

    @Column ( name = "type_short_name")
    private String shortTypeName;

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
        setMetadata(new Metadata("\buffTypes", false, toString()));
    }

    // GETTERS
    public int getBuffTypeID()
    {
        return buffTypeID;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public String getShortTypeName()
    {
        return shortTypeName;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setBuffTypeID(int buffTypeID)
    {
        this.buffTypeID = buffTypeID;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public void setShortTypeName(String shortTypeName)
    {
        this.shortTypeName = shortTypeName;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods
    @Override
    public String toString()
    {
        return getTypeName();
    }
}
