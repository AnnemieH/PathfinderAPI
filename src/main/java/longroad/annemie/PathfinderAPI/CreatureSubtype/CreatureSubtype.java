package longroad.annemie.PathfinderAPI.CreatureSubtype;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.CreatureType.CreatureType;

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

    // GETTERS

    public int getCreatureTypeID()
    {
        return creatureSubtypeID;
    }

    public String getName()
    {
        return name;
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
}
