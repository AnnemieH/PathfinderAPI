package longroad.annemie.PathfinderAPI.PFCharacterCharClass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PFCharacterCharClassKey
{
    @Column ( name = "Character_character_id" )
    private int characterID;

    @Column ( name = "Classes_class_id" )
    private int classID;

    // GETTERS
    public int getCharacterID()
    {
        return characterID;
    }

    public int getClassID()
    {
        return classID;
    }

    // SETTERS
    public void setCharacterID(int characterID)
    {
        this.characterID = characterID;
    }

    public void setClassID(int classID)
    {
        this.classID = classID;
    }

    // CONSTRUCTORS
    public PFCharacterCharClassKey()
    {

    }
    public PFCharacterCharClassKey(int characterID, int classID)
    {
        this.characterID = characterID;
        this.classID = classID;
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
        PFCharacterCharClassKey that = (PFCharacterCharClassKey) o;
        return characterID == that.characterID && classID == that.classID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(characterID, classID);
    }
}
