package longroad.annemie.PathfinderAPI.Save;

import longroad.annemie.PathfinderAPI.Attribute.Attribute;
import jakarta.persistence.*;

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
}
