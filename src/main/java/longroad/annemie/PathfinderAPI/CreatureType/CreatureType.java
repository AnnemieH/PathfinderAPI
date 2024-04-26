package longroad.annemie.PathfinderAPI.CreatureType;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.CreatureSubtype.CreatureSubtype;

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

    // GETTERS

    public int getCreatureTypeID()
    {
        return creatureTypeID;
    }

    public String getName()
    {
        return name;
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
}
