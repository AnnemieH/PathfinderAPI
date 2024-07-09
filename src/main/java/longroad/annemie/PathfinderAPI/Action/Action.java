package longroad.annemie.PathfinderAPI.Action;

import jakarta.persistence.*;

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

    // GETTERS

    public short getActionID()
    {
        return actionID;
    }

    public String getActionName()
    {
        return actionName;
    }
}
