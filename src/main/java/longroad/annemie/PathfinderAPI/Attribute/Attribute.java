package longroad.annemie.PathfinderAPI.Attribute;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table (name="attribute")
public class Attribute
{
    @Id
    @Column ( name = "attribute_id", unique = true )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short attributeID;

    @Column ( name = "attribute_name")
    private String attributeName;

    @Column ( name = "short_name")
    private String shortName;

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
        setMetadata(new Metadata("/attribute", false, toString()));
    }

    // GETTERS

    public short getAttributeID()
    {
        return attributeID;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public String getShortName()
    {
        return shortName;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS

    public void setAttributeID(short attributeID)
    {
        this.attributeID = attributeID;
    }

    public void setAttributeName(String attributeName)
    {
        this.attributeName = attributeName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods

    @Override
    public String toString()
    {
        return getAttributeName();
    }
}
