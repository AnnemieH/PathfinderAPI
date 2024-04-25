package longroad.annemie.PathfinderAPI.BuffType;

import jakarta.persistence.*;

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
}
