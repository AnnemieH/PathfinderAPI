package longroad.annemie.PathfinderAPI.Time;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table( name = "time" )
public class Time
{
    @Id
    @Column( name = "time_id" )
    @GeneratedValue( strategy =  GenerationType.IDENTITY )
    private short timeID;

    @Column( name = "time_name" )
    private String timeName;

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
        setMetadata(new Metadata("/times", false, toString()));
    }

    // GETTERS
    public short getTimeID()
    {
        return timeID;
    }

    public String getTimeName()
    {
        return timeName;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setTimeID(short timeID)
    {
        this.timeID = timeID;
    }

    public void setTimeName(String timeName)
    {
        this.timeName = timeName;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods

    @Override
    public String toString()
    {
        return getTimeName();
    }
}
