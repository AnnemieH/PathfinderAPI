package longroad.annemie.PathfinderAPI.SpellSchool;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;

@Entity
@Table( name="spell_school" )
public class SpellSchool
{
    @Id
    @Column ( name = "spell_school_id", unique = true )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private short spellSchoolID;

    @Column ( name = "spell_school_name" )
    private String spellSchoolName;

    @ManyToOne( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "subschool_of" )
    @JsonInclude ( JsonInclude.Include.NON_NULL )
    private SpellSchool subschoolOf;

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
        setMetadata(new Metadata("/spellSchools", false, toString()));
    }

    // GETTERS
    public short getSpellSchoolID()
    {
        return spellSchoolID;
    }

    public String getSpellSchoolName()
    {
        return spellSchoolName;
    }

    public SpellSchool getSubschoolOf()
    {
        return subschoolOf;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS

    public void setSpellSchoolID(short spellSchoolID)
    {
        this.spellSchoolID = spellSchoolID;
    }

    public void setSpellSchoolName(String spellSchoolName)
    {
        this.spellSchoolName = spellSchoolName;
    }

    public void setSubschoolOf(SpellSchool subschoolOf)
    {
        this.subschoolOf = subschoolOf;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods

    @Override
    public String toString()
    {
        String output;

        if ( getSubschoolOf() != null )
        {
            output = getSubschoolOf().getSpellSchoolName();
            output += " (" + getSpellSchoolName() + ")";
        }
        else
        {
            output = getSpellSchoolName();
        }
        return output;
    }
}
