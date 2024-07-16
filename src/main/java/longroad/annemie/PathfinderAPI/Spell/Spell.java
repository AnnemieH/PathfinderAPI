package longroad.annemie.PathfinderAPI.Spell;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
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
        setMetadata(new Metadata("/spells", true, toString()));
    }

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

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTErS
    public void setSpellID(int spellID)
    {
        this.spellID = spellID;
    }

    public void setSpellname(String spellname)
    {
        this.spellname = spellname;
    }

    public void setSpellLists(List<SpellListSpell> spellLists)
    {
        this.spellLists = spellLists;
    }

    public void setCastingTime(Action castingTime)
    {
        this.castingTime = castingTime;
    }

    public void setDurationIncrement(Action durationIncrement)
    {
        this.durationIncrement = durationIncrement;
    }

    public void setDurationMultiplier(short durationMultiplier)
    {
        this.durationMultiplier = durationMultiplier;
    }

    public void setDurationScaleByNLevels(short durationScaleByNLevels)
    {
        this.durationScaleByNLevels = durationScaleByNLevels;
    }

    public void setRange(SpellRange range)
    {
        this.range = range;
    }

    public void setRangeMultiplier(short rangeMultiplier)
    {
        this.rangeMultiplier = rangeMultiplier;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public void setSchool(SpellSchool school)
    {
        this.school = school;
    }

    public void setSave(Save save)
    {
        this.save = save;
    }

    public void setSpellResistance(boolean spellResistance)
    {
        this.spellResistance = spellResistance;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods
    @Override
    public String toString()
    {
        return getSpellname();
    }
}
