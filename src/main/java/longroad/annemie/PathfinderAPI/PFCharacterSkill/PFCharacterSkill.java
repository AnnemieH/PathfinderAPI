package longroad.annemie.PathfinderAPI.PFCharacterSkill;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
import longroad.annemie.PathfinderAPI.PFCharacter.PFCharacter;
import longroad.annemie.PathfinderAPI.Skill.Skill;

import java.util.Objects;

@Entity
@Table ( name = "character_skill" )
public class PFCharacterSkill
{
    @EmbeddedId
    private PFCharacterSkillKey id;

    @ManyToOne
    @MapsId ( "characterID" )
    @JoinColumn ( name = "character_id" )
    @JsonIgnore
    private PFCharacter character;

    @ManyToOne
    @MapsId ( "skillID" )
    @JoinColumn ( name = "skill_id" )
    private Skill skill;

    @Column ( name = "ranks" )
    private short ranks;

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
        setMetadata(new Metadata("/characterSkills", true, toString()));
    }

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

    public Metadata getMetadata()
    {
        return metadata;
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

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // OVERRIDDEN METHODS
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        PFCharacterSkill that = (PFCharacterSkill) o;
        return ranks == that.ranks && Objects.equals(id, that.id) && Objects.equals(character, that.character) && Objects.equals(skill, that.skill);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, character, skill, ranks);
    }

    @Override
    public String toString()
    {
        return getSkill().getSkillName() + ": " +
               getRanks() + " ranks";
    }
}
