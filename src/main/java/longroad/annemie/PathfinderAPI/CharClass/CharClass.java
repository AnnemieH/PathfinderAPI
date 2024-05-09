package longroad.annemie.PathfinderAPI.CharClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import longroad.annemie.PathfinderAPI.Attribute.Attribute;
import longroad.annemie.PathfinderAPI.ClassBuff.ClassBuff;
import longroad.annemie.PathfinderAPI.CharClassSkill.CharClassSkill;
import longroad.annemie.PathfinderAPI.MagicSource.MagicSource;
import longroad.annemie.PathfinderAPI.SpellList.SpellList;
import longroad.annemie.PathfinderAPI.SpellcasterType.SpellcasterType;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="char_class")
public class CharClass
{
    @Id
    @Column (name="class_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classID;

    @Column (name = "class_name")
    private String className;


    @ManyToOne ( cascade = CascadeType.MERGE )
    @JoinColumn (name="archetype_of")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private CharClass archetype;

    @Column ( name="is_prestige" )
    private boolean isPrestige;

    @OneToMany ( mappedBy = "currClass", orphanRemoval = true )
    private Set<ClassBuff> buffs;

    @Column (name="hit_die")
    private short hitDie;
    @Column (name="bab")
    private String bab;

    @Column (name="fortitude")
    private String fortitude;

    @Column (name = "reflex")
    private String reflex;

    @Column (name = "will")
    private String will;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn( name = "spellcaster_type" )
    private SpellcasterType spellcasterType;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn( name = "casting_ability" )
    private Attribute castingAbility;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn( name = "magic_source")
    private MagicSource magicSource;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn( name = "spell_list" )
    private SpellList spellList;

    @Column( name = "spells_per_day" )
    private String spellsPerDay;

    @Column( name = "spells_known" )
    private String spellsKnown;

    @Column ( name = "skill_ranks")
    private short skillRanks;

    @OneToMany ( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "char_class_id" )
    private Set<CharClassSkill> classSkills = new HashSet<>();


    // GETTERS

    public int getClassID()
    {
        return classID;
    }

    public String getClassName()
    {
        return className;
    }

    public CharClass getArchetype()
    {
        return archetype;
    }

    public boolean getIsPrestige()
    {
        return isPrestige;
    }

    public short getHitDie()
    {
        return hitDie;
    }

    public String getBab()
    {
        return bab;
    }

    public String getFortitude()
    {
        return fortitude;
    }

    public String getReflex()
    {
        return reflex;
    }

    public String getWill()
    {
        return will;
    }

    public SpellcasterType getSpellcasterType()
    {
        return spellcasterType;
    }

    public Attribute getCastingAbility()
    {
        return castingAbility;
    }

    public MagicSource getMagicSource()
    {
        return magicSource;
    }

    public SpellList getSpellList()
    {
        return spellList;
    }

    public String getSpellsPerDay()
    {
        return spellsPerDay;
    }

    public String getSpellsKnown()
    {
        return spellsKnown;
    }

    public Set<ClassBuff> getBuffs()
    {
        return buffs;
    }

    public short getSkillRanks()
    {
        return skillRanks;
    }

    public Set<CharClassSkill> getClassSkills()
    {
        return classSkills;
    }

    // SETTERS
    public void setClassID(int classID)
    {
        this.classID = classID;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public void setArchetype(CharClass archetype)
    {
        this.archetype = archetype;
    }

    public void setIsPrestige(boolean prestige)
    {
        isPrestige = prestige;
    }

    public void setHitDie(short hitDie)
    {
        this.hitDie = hitDie;
    }

    public void setBab( String bab )
    {
        this.bab = bab;
    }


    public void setFortitude(String fortitude)
    {
        this.fortitude = fortitude;
    }

    public void setReflex(String reflex)
    {
        this.reflex = reflex;
    }

    public void setWill(String will)
    {
        this.will = will;
    }

    public void setSpellcasterType(SpellcasterType spellcasterType)
    {
        this.spellcasterType = spellcasterType;
    }

    public void setCastingAbility(Attribute castingAbility)
    {
        this.castingAbility = castingAbility;
    }

    public void setMagicSource(MagicSource magicSource)
    {
        this.magicSource = magicSource;
    }

    public void setSpellList(SpellList spellList)
    {
        this.spellList = spellList;
    }

    public void setSpellsPerDay(String spellsPerDay)
    {
        this.spellsPerDay = spellsPerDay;
    }

    public void setSpellsKnown(String spellsKnown)
    {
        this.spellsKnown = spellsKnown;
    }

    public void setBuffs(Set<ClassBuff> buffs)
    {
        this.buffs = buffs;
    }

    public void setSkillRanks(short skillRanks)
    {
        this.skillRanks = skillRanks;
    }

    public void setClassSkills(Set<CharClassSkill> classSkills)
    {
        this.classSkills = classSkills;
    }
}
