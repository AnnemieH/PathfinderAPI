package longroad.annemie.PathfinderAPI.BuffCategory;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table( name = "buff_category" )
public class BuffCategory
{
    @Id
    @Column ( name = "buff_category_id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private short buffCategoryID;

    @Column ( name = "name" )
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
        setMetadata(new Metadata("/buffCategories", false, toString()));
    }

    // GETTERS
    public short getBuffCategoryID()
    {
        return buffCategoryID;
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

    public void setBuffCategoryID(short buffCategoryID)
    {
        this.buffCategoryID = buffCategoryID;
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
