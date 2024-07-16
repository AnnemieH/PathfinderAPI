package longroad.annemie.PathfinderAPI.CharClassSkill;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import longroad.annemie.PathfinderAPI.CharClass.CharClass;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
import longroad.annemie.PathfinderAPI.Skill.Skill;
import jakarta.persistence.*;

@Entity
@Table( name = "char_class_skill")
public class CharClassSkill
{
    @EmbeddedId
    private CharClassSkillKey id;

    @ManyToOne
    @MapsId("classID")
    @JoinColumn ( name = "char_class_id" )
    @JsonIgnore
    private CharClass charClass;

    @ManyToOne
    @MapsId("skillID")
    @JoinColumn ( name = "skill_id")
    private Skill skill;

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
        setMetadata(new Metadata("/charClassSkills", true, toString()));
    }

    // GETTERS
    public CharClassSkillKey getId()
    {
        return id;
    }

    public CharClass getCharClass()
    {
        return charClass;
    }

    public Skill getSkill()
    {
        return skill;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setId(CharClassSkillKey id)
    {
        this.id = id;
    }

    public void setCharClass(CharClass charClass)
    {
        this.charClass = charClass;
    }

    public void setSkill(Skill skill)
    {
        this.skill = skill;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // Overridden methods
    @Override
    public String toString()
    {
        return getSkill().toString() + " is a class skill of " +
               getCharClass().toString();
    }
}
