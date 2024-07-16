package longroad.annemie.PathfinderAPI.SpellList;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
import longroad.annemie.PathfinderAPI.SpellListSpell.SpellListSpell;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table ( name = "spell_list" )
public class SpellList
{
    @Id
    @Column ( name = "spell_list_id", unique = true )
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private short spellListID;

    @Column ( name = "spell_list_name" )
    private String spellListName;

    @OneToMany ( mappedBy = "spellList" )
    @JsonManagedReference (value = "spellListSpells" )
    private List<SpellListSpell> spells = new ArrayList<>();

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
        setMetadata(new Metadata("/spelllists", false, toString()));
    }

    // GETTERS
    public short getSpellListID()
    {
        return spellListID;
    }

    public String getSpellListName()
    {
        return spellListName;
    }

    public List<SpellListSpell> getSpells()
    {
        return spells;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS

    public void setSpellListID(short spellListID)
    {
        this.spellListID = spellListID;
    }

    public void setSpellListName(String spellListName)
    {
        this.spellListName = spellListName;
    }

    public void setSpells(List<SpellListSpell> spells)
    {
        this.spells = spells;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // OvERRIDDEN METHODS
    @Override
    public String toString()
    {
        return getSpellListName();
    }
}
