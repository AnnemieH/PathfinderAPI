package longroad.annemie.PathfinderAPI.PFCharacterCharClass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.CharClass.CharClass;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
import longroad.annemie.PathfinderAPI.PFCharacter.PFCharacter;

import java.util.Objects;

@Entity
@Table ( name = "character_class" )
public class PFCharacterCharClass
{
    @EmbeddedId
    private PFCharacterCharClassKey id;

    @ManyToOne
    @MapsId ( "characterID" )
    @JoinColumn ( name = "Character_character_id" )
    @JsonIgnore
    private PFCharacter character;

    @ManyToOne
    @MapsId ( "classID" )
    @JoinColumn ( name = "classes_class_id" )
    private CharClass charClass;

    @Column ( name = "level" )
    private short level;

    @Column ( name = "hp" )
    private int hp;

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
        setMetadata(new Metadata("/characterCharClasses", true, toString()));
    }

    // GETTERS
    public PFCharacterCharClassKey getId()
    {
        return id;
    }

    public PFCharacter getCharacter()
    {
        return character;
    }

    public CharClass getCharClass()
    {
        return charClass;
    }

    public short getLevel()
    {
        return level;
    }

    public int getHp()
    {
        return hp;
    }

    public Metadata getMetadata()
    {
        return metadata;
    }

    // SETTERS
    public void setId(PFCharacterCharClassKey id)
    {
        this.id = id;
    }

    public void setCharacter(PFCharacter character)
    {
        this.character = character;
    }

    public void setCharClass(CharClass charClass)
    {
        this.charClass = charClass;
    }

    public void setLevel(short level)
    {
        this.level = level;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
    }

    // CONSTRUCTORS
    public PFCharacterCharClass ()
    {

    }

    public PFCharacterCharClass (
            PFCharacterCharClassKey id,
            PFCharacter pfCharacter,
            CharClass charClass,
            short level
            )
    {
        this.id = id;
        this.character = pfCharacter;
        this.charClass = charClass;
        this.level = level;
    }

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
        PFCharacterCharClass that = (PFCharacterCharClass) o;
        return level == that.level && Objects.equals(id, that.id) && Objects.equals(character, that.character) && Objects.equals(charClass, that.charClass);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, character, charClass, level);
    }

    @Override
    public String toString()
    {
        return getCharClass().toString();
    }
}
