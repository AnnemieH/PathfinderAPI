package longroad.annemie.PathfinderAPI.Spell;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import longroad.annemie.PathfinderAPI.Save.Save;
import longroad.annemie.PathfinderAPI.SpellListSpell.SpellListSpell;
import longroad.annemie.PathfinderAPI.SpellSchool.SpellSchool;
import longroad.annemie.PathfinderAPI.SpellRange.SpellRange;
import longroad.annemie.PathfinderAPI.Action.Action;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "spell" )
public class Spell
{
    @Id
    @Column ( name = "spell_id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int spellID;

    @Column ( name = "spell_name" )
    private String spellname;

    @OneToMany ( mappedBy = "spell" )
    @JsonManagedReference ( value = "spellSpellList" )
    private List<SpellListSpell> spellLists = new ArrayList<>();

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "casting_time" )
    private Action castingTime;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "duration_increment" )
    private Action durationIncrement;

    @Column ( name = "duration_multiplier" )
    private short durationMultiplier;

    @Column ( name = "duration_scale_by_n_levels" )
    private short durationScaleByNLevels;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "range" )
    private SpellRange range;

    @Column ( name = "range_multiplier" )
    private short rangeMultiplier;

    @Column ( name = "target")
    private String target;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "school" )
    private SpellSchool school;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "save" )
    private Save save;

    @Column ( name = "spell_resistance" )
    private boolean spellResistance;

    // GETTERS

    public int getSpellID()
    {
        return spellID;
    }

    public String getSpellname()
    {
        return spellname;
    }

    public List<SpellListSpell> getSpellLists()
    {
        return spellLists;
    }

    public Action getCastingTime()
    {
        return castingTime;
    }

    public Action getDurationIncrement()
    {
        return durationIncrement;
    }

    public short getDurationMultiplier()
    {
        return durationMultiplier;
    }

    public short getDurationScaleByNLevels()
    {
        return durationScaleByNLevels;
    }

    public SpellRange getRange()
    {
        return range;
    }

    public short getRangeMultiplier()
    {
        return rangeMultiplier;
    }

    public String getTarget()
    {
        return target;
    }

    public SpellSchool getSchool()
    {
        return school;
    }

    public Save getSave()
    {
        return save;
    }

    public boolean getSpellResistance()
    {
        return spellResistance;
    }
}
