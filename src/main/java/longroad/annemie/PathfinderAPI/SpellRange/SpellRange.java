package longroad.annemie.PathfinderAPI.SpellRange;

import jakarta.persistence.*;

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

    // GETTERS

    public short getSpellRangeID()
    {
        return spellRangeID;
    }

    public String getSpellRangeName()
    {
        return SpellRangeName;
    }
}
