package longroad.annemie.PathfinderAPI.Size;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

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
        setMetadata(new Metadata("/sizes", false, toString()));
    }


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

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS

    public void setSizeID(short sizeID)
    {
        this.sizeID = sizeID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSizeModifier(short sizeModifier)
    {
        this.sizeModifier = sizeModifier;
    }

    public void setFlightModifier(short flightModifier)
    {
        this.flightModifier = flightModifier;
    }

    public void setSpace(float space)
    {
        this.space = space;
    }

    public void setReach(short reach)
    {
        this.reach = reach;
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
