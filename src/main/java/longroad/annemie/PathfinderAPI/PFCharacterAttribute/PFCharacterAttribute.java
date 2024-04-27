package longroad.annemie.PathfinderAPI.PFCharacterAttribute;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Attribute.Attribute;
import longroad.annemie.PathfinderAPI.PFCharacter.PFCharacter;

@Entity
@Table ( name = "character_attribute" )
public class PFCharacterAttribute
{
    @EmbeddedId PFCharacterAttributeKey id;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "characterID" )
    @JoinColumn ( name = "character_id" )
    @JsonBackReference ( value = "characterAttribute")
    private PFCharacter character;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "attributeID" )
    @JoinColumn ( name = "attribute_id" )
    private Attribute attribute;

    @Column( name = "value" )
    private short value;

    // GETTERS

    public PFCharacterAttributeKey getId()
    {
        return id;
    }

    public PFCharacter getCharacter()
    {
        return character;
    }

    public Attribute getAttribute()
    {
        return attribute;
    }

    public short getValue()
    {
        return value;
    }

    // SETTERS
    public void setId(PFCharacterAttributeKey id)
    {
        this.id = id;
    }

    public void setCharacter(PFCharacter character)
    {
        this.character = character;
    }

    public void setAttribute(Attribute attribute)
    {
        this.attribute = attribute;
    }

    public void setValue(short value)
    {
        this.value = value;
    }
}
