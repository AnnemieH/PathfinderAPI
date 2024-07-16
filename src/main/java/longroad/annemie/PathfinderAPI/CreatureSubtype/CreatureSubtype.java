package longroad.annemie.PathfinderAPI.CreatureSubtype;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table ( name = "creature_subtype" )
public class CreatureSubtype
{
    @Id
    @Column( name = "creature_subtype_id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int creatureSubtypeID;

    @Column( name = "name" )
    private String name;

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
        setMetadata(new Metadata("/creatureSubtypes", false, toString()));
    }

    // GETTERS
    public int getCreatureTypeID()
    {
        return creatureSubtypeID;
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
    public void setCreatureTypeID(int creatureSubtypeID)
    {
        this.creatureSubtypeID = creatureSubtypeID;
    }

    public void setName(String name)
    {
        this.name = name;
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
