package longroad.annemie.PathfinderAPI.CreatureType;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.CreatureSubtype.CreatureSubtype;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

import java.util.Set;

@Entity
@Table( name = "creature_type" )
public class CreatureType
{
    @Id
    @Column( name = "creature_type_id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int creatureTypeID;

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
        setMetadata(new Metadata("/creatureTypes", false, toString()));
    }

    // GETTERS
    public int getCreatureTypeID()
    {
        return creatureTypeID;
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
    public void setCreatureTypeID(int creatureTypeID)
    {
        this.creatureTypeID = creatureTypeID;
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
