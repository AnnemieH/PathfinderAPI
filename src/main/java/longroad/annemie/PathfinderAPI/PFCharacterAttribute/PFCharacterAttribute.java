package longroad.annemie.PathfinderAPI.PFCharacterAttribute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Attribute.Attribute;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
import longroad.annemie.PathfinderAPI.PFCharacter.PFCharacter;

import java.util.Objects;

@Entity
@Table ( name = "character_attribute" )
public class PFCharacterAttribute
{
    @EmbeddedId
    private PFCharacterAttributeKey id;

    @ManyToOne
    @MapsId ( "characterID" )
    @JoinColumn ( name = "character_id" )
    @JsonIgnore
    private PFCharacter character;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "attributeID" )
    @JoinColumn ( name = "attribute_id" )
    private Attribute attribute;

    @Column( name = "value" )
    private short value;

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
        setMetadata(new Metadata("/characterAttributes", true, toString()));
    }

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

    public Metadata getMetadata()
    {
        return metadata;
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

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // CONSTRUCTORS
    public PFCharacterAttribute ()
    {
        this.id = new PFCharacterAttributeKey();
        this.character = new PFCharacter();
        this.attribute = new Attribute();
        this.value = 0;
    }

    public PFCharacterAttribute (
            PFCharacterAttributeKey id,
            PFCharacter character,
            Attribute attribute,
            short value
                         )
    {
        this.id = id;
        this.character = character;
        this.attribute = attribute;
        this.value = value;
    }


    // Overridden methods
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
        PFCharacterAttribute attribute1 = (PFCharacterAttribute) o;
        return value == attribute1.value && Objects.equals(id, attribute1.id) && Objects.equals(character, attribute1.character) && Objects.equals(attribute, attribute1.attribute);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, character, attribute, value);
    }

    @Override
    public String toString()
    {
        return getAttribute().getAttributeName() + ": " + getValue();
    }
}
