package longroad.annemie.PathfinderAPI.PFCharacterCharClass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.CharClass.CharClass;
import longroad.annemie.PathfinderAPI.PFCharacter.PFCharacter;

@Entity
@Table ( name = "character_class" )
public class PFCharacterCharClass
{
    @EmbeddedId
    private PFCharacterCharClassKey id;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "characterID" )
    @JoinColumn ( name = "Character_character_id" )
    @JsonBackReference ( value = "characterClass" )
    private PFCharacter character;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @MapsId ( "classID" )
    @JoinColumn ( name = "classes_class_id" )
    private CharClass charClass;

    @Column ( name = "level" )
    private short level;

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
}
