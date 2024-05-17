package longroad.annemie.PathfinderAPI.CharClassSkill;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import longroad.annemie.PathfinderAPI.CharClass.CharClass;
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
}
