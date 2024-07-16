package longroad.annemie.PathfinderAPI.Action;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table( name = "action" )
public class Action
{
    @Id
    @Column( name = "action_id", unique = true )
    @GeneratedValue( strategy = GenerationType.AUTO )
    private short actionID;

    @Column( name = "action_name" )
    private String actionName;

    @Transient
    private Metadata metadata;

    // Initialisation
    @PostLoad
    public void init()
    {
        metadataInit();
    }

    // Make sure metadata is initialised properly
    public void metadataInit()
    {
        setMetadata(new Metadata("/action", false, toString() ) );
    }

    // GETTERS
    public short getActionID()
    {
        return actionID;
    }

    public String getActionName()
    {
        return actionName;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods

    @Override
    public String toString()
    {
        return getActionName() + " action";
    }
}
