package longroad.annemie.PathfinderAPI.Size;

import jakarta.persistence.*;

@Entity
@Table( name = "size" )
public class Size
{
    @Id
    @Column ( name = "size_id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private short sizeID;

    @Column ( name = "name" )
    private String name;

    @Column ( name = "size_modifier" )
    private short sizeModifier;

    // NB: Stealth modifier is 2x flightModifier
    @Column ( name = "flight_modifier" )
    private short flightModifier;

    @Column ( name = "space" )
    private float space;

    @Column ( name = "reach" )
    private short reach;

    // GETTERS

    public short getSizeID()
    {
        return sizeID;
    }

    public String getName()
    {
        return name;
    }

    public short getSizeModifier()
    {
        return sizeModifier;
    }

    public short getFlightModifier()
    {
        return flightModifier;
    }

    public float getSpace()
    {
        return space;
    }

    public short getReach()
    {
        return reach;
    }
}
