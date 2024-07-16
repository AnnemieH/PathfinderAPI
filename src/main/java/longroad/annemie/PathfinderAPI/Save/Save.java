package longroad.annemie.PathfinderAPI.Save;

import longroad.annemie.PathfinderAPI.Attribute.Attribute;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table( name = "save")
public class Save
{
    @Id
    @Column ( name = "save_id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private short saveID;

    @Column ( name = "save_name" )
    private String saveName;

    @Column ( name = "save_name_short" )
    private String saveNameShort;

    @OneToOne ( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "attribute" )
    private Attribute attribute;

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
        setMetadata(new Metadata("/saves", false, toString()));
    }

    // GETTERS
    public short getSaveID()
    {
        return saveID;
    }

    public String getSaveName()
    {
        return saveName;
    }

    public String getSaveNameShort()
    {
        return saveNameShort;
    }

    public Attribute getAttribute()
    {
        return attribute;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS

    public void setSaveID(short saveID)
    {
        this.saveID = saveID;
    }

    public void setSaveName(String saveName)
    {
        this.saveName = saveName;
    }

    public void setSaveNameShort(String saveNameShort)
    {
        this.saveNameShort = saveNameShort;
    }

    public void setAttribute(Attribute attribute)
    {
        this.attribute = attribute;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods

    @Override
    public String toString()
    {
        return getSaveName();
    }
}
