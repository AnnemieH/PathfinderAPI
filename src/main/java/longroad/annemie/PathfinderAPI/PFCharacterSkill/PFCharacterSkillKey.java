package longroad.annemie.PathfinderAPI.PFCharacterSkill;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PFCharacterSkillKey implements Serializable
{
    @Column ( name = "character_id" )
    private int characterID;

    @Column ( name = "skill_id" )
    private short skillID;

    // GETTERS
    public int getCharacterID()
    {
        return characterID;
    }

    public short getSkillID()
    {
        return skillID;
    }

    // SETTERS
    public void setCharacterID(int characterID)
    {
        this.characterID = characterID;
    }

    public void setSkillID(short skillID)
    {
        this.skillID = skillID;
    }

    // CONSTRUCTORS
    public PFCharacterSkillKey ()
    {

    }

    public PFCharacterSkillKey ( int characterID, short skillID )
    {
        this.characterID = characterID;
        this.skillID = skillID;
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
        PFCharacterSkillKey that = (PFCharacterSkillKey) o;
        return characterID == that.characterID && skillID == that.skillID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(characterID, skillID);
    }
}
