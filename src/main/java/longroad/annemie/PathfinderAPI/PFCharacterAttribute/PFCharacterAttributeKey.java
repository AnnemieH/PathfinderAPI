package longroad.annemie.PathfinderAPI.PFCharacterAttribute;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PFCharacterAttributeKey implements Serializable
{
    @Column ( name = "character_id" )
    private int characterID;

    @Column ( name = "attribute_id" )
    private short attributeID;

    // GETTERS
    public int getCharacterID()
    {
        return characterID;
    }

    public short getAttributeID()
    {
        return attributeID;
    }

    // SETTERS
    public void setCharacterID(int characterID)
    {
        this.characterID = characterID;
    }

    public void setAttributeID(short attributeID)
    {
        this.attributeID = attributeID;
    }

    // CONSTRUCTORS
    public PFCharacterAttributeKey ()
    {

    }

    public PFCharacterAttributeKey ( int characterID, short attributeID )
    {
        this.characterID = characterID;
        this.attributeID = attributeID;
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
        PFCharacterAttributeKey that = (PFCharacterAttributeKey) o;
        return characterID == that.characterID && attributeID == that.attributeID;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(characterID, attributeID);
    }
}
