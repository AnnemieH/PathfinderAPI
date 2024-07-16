package longroad.annemie.PathfinderAPI.SpellcasterType;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table( name="spellcaster_type" )
public class SpellcasterType
{
    @Id
    @Column ( name="spellcaster_type_id", unique = true )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short spellcasterTypeID;

    @Column ( name="name")
    private String spellcasterTypeName;

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
        setMetadata(new Metadata("/spellcasterTypes", false, toString()));
    }


    // GETTERS
    public short getSpellcasterTypeID()
    {
        return spellcasterTypeID;
    }

    public String getSpellcasterTypeName()
    {
        return spellcasterTypeName;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setSpellcasterTypeID(short spellcasterTypeID)
    {
        this.spellcasterTypeID = spellcasterTypeID;
    }

    public void setSpellcasterTypeName(String spellcasterTypeName)
    {
        this.spellcasterTypeName = spellcasterTypeName;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods
    @Override
    public String toString()
    {
        return getSpellcasterTypeName();
    }
}
