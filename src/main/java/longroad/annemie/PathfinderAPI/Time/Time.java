package longroad.annemie.PathfinderAPI.Time;

import jakarta.persistence.*;

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

    // GETTERS

    public short getTimeID()
    {
        return timeID;
    }

    public String getTimeName()
    {
        return timeName;
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
}
