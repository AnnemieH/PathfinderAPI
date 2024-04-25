package longroad.annemie.PathfinderAPI.SpellcasterType;

import jakarta.persistence.*;

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

    // GETTERS

    public short getSpellcasterTypeID()
    {
        return spellcasterTypeID;
    }

    public String getSpellcasterTypeName()
    {
        return spellcasterTypeName;
    }
}
