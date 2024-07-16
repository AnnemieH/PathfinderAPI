package longroad.annemie.PathfinderAPI.SpellListSpell;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
import longroad.annemie.PathfinderAPI.Spell.Spell;
import longroad.annemie.PathfinderAPI.SpellList.SpellList;
import jakarta.persistence.*;

@Entity
@Table ( name = "spell_list_spell" )
public class SpellListSpell
{
    @EmbeddedId
    private SpellListSpellKey id;

    @ManyToOne
    @MapsId( "spellID" )
    @JoinColumn ( name = "spell_id" )
    @JsonBackReference ( value = "spellSpellList" )
    private Spell spell;

    @ManyToOne
    @MapsId ( "spellListID" )
    @JoinColumn ( name = "spell_list_id" )
    @JsonBackReference ( value = "spellListSpells" )
    private SpellList spellList;

    @Column ( name = "level" )
    private short level;

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
        setMetadata(new Metadata("/spellListSpells", true, toString()));
    }

    // GETTERS
    public SpellListSpellKey getId()
    {
        return id;
    }

    public Spell getSpell()
    {
        return spell;
    }

    public SpellList getSpellList()
    {
        return spellList;
    }

    public short getLevel()
    {
        return level;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS

    public void setId(SpellListSpellKey id)
    {
        this.id = id;
    }

    public void setSpell(Spell spell)
    {
        this.spell = spell;
    }

    public void setSpellList(SpellList spellList)
    {
        this.spellList = spellList;
    }

    public void setLevel(short level)
    {
        this.level = level;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods
    @Override
    public String toString()
    {
        return getSpell().getSpellname() + ": " +
               getSpellList().getSpellListName() + " " +
               getLevel();
    }
}
