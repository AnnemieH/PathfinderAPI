package longroad.annemie.PathfinderAPI.PFCharacterSkill;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.PFCharacter.PFCharacter;
import longroad.annemie.PathfinderAPI.Skill.Skill;

@Entity
@Table ( name = "character_skill" )
public class PFCharacterSkill
{
    @EmbeddedId
    private PFCharacterSkillKey id;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "characterID" )
    @JoinColumn ( name = "character_id" )
    @JsonBackReference ( value = "characterSkill" )
    private PFCharacter character;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "skillID" )
    @JoinColumn ( name = "skill_id" )
    private Skill skill;

    @Column ( name = "ranks" )
    private short ranks;

    // GETTERS
    public PFCharacterSkillKey getId()
    {
        return id;
    }

    public PFCharacter getCharacter()
    {
        return character;
    }

    public Skill getSkill()
    {
        return skill;
    }

    public short getRanks()
    {
        return ranks;
    }

    // SETTERS
    public void setId(PFCharacterSkillKey id)
    {
        this.id = id;
    }

    public void setCharacter(PFCharacter character)
    {
        this.character = character;
    }

    public void setSkill(Skill skill)
    {
        this.skill = skill;
    }

    public void setRanks(short ranks)
    {
        this.ranks = ranks;
    }
}
