package longroad.annemie.PathfinderAPI.SpellRange;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table ( name = "spell_range" )
public class SpellRange
{
    @Id
    @Column ( name = "spell_range_id", unique = true )
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private short spellRangeID;

    @Column ( name = "spell_range_name" )
    private String SpellRangeName;

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
        setMetadata(new Metadata("/spellRanges", false, toString()));
    }

    // GETTERS
    public short getSpellRangeID()
    {
        return spellRangeID;
    }

    public String getSpellRangeName()
    {
        return SpellRangeName;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setSpellRangeID(short spellRangeID)
    {
        this.spellRangeID = spellRangeID;
    }

    public void setSpellRangeName(String spellRangeName)
    {
        SpellRangeName = spellRangeName;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods
    @Override
    public String toString()
    {
        return getSpellRangeName();
    }
}
