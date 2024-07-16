package longroad.annemie.PathfinderAPI.MagicSource;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table ( name = "magic_source" )
public class MagicSource
{
    @Id
    @Column ( name = "magic_source_id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private short magicSourceID;

    @Column ( name = "magic_source_name" )
    private String magicSourceName;

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
        setMetadata(new Metadata("/magicSources", false, toString()));
    }

    // GETTERS
    public short getMagicSourceID()
    {
        return magicSourceID;
    }

    public String getMagicSourceName()
    {
        return magicSourceName;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setMagicSourceID(short magicSourceID)
    {
        this.magicSourceID = magicSourceID;
    }

    public void setMagicSourceName(String magicSourceName)
    {
        this.magicSourceName = magicSourceName;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods
    @Override
    public String toString()
    {
        return getMagicSourceName();
    }
}
