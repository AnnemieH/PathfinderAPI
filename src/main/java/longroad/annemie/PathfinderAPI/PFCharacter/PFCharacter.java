package longroad.annemie.PathfinderAPI.PFCharacter;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.PFCharacterAttribute.PFCharacterAttribute;
import longroad.annemie.PathfinderAPI.PFCharacterCharClass.PFCharacterCharClass;
import longroad.annemie.PathfinderAPI.PFCharacterCharClass.PFCharacterCharClassKey;
import longroad.annemie.PathfinderAPI.PFCharacterSkill.PFCharacterSkill;
import longroad.annemie.PathfinderAPI.Race.Race;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table ( name = "character_table" )
public class PFCharacter
{
    @Id
    @Column ( name = "character_id", unique = true )
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private int characterID;

    @Column ( name = "character_name" )
    private String name;

    @ManyToOne ( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "race_id" )
    private Race race;

    @OneToMany ( mappedBy = "character", orphanRemoval = true )
    private Set <PFCharacterCharClass> charClasses = new HashSet<>();

    @OneToMany( mappedBy = "character", orphanRemoval = true )
    private Set <PFCharacterAttribute> attributes = new HashSet<>();

    @OneToMany ( mappedBy = "character", orphanRemoval = true )
    private Set<PFCharacterSkill> skillRanks = new HashSet<>();

    // MEMBER FUNCTIONS
    // Add ids to everything that needs them
    public void addID( int charID )
    {
        this.characterID = charID;


        // Assign IDs for character classes
        for ( PFCharacterCharClass charClass : charClasses )
        {
            charClass.getId().setCharacterID( charID );
        }

        // Assign IDs for attributes
        for ( PFCharacterAttribute attribute : attributes )
        {
            attribute.getId().setCharacterID( charID );
        }
    }

    // GETTERS
    public int getCharacterID()
    {
        return characterID;
    }

    public String getName()
    {
        return name;
    }

    public Race getRace()
    {
        return race;
    }

    public Set<PFCharacterCharClass> getCharClasses()
    {
        return charClasses;
    }

    public Set<PFCharacterAttribute> getAttributes()
    {
        return attributes;
    }

    public Set<PFCharacterSkill> getSkillRanks()
    {
        return skillRanks;
    }

    // SETTERS
    public void setCharacterID(int characterID)
    {
        this.characterID = characterID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRace(Race race)
    {
        this.race = race;
    }

    public void setCharClasses(Set<PFCharacterCharClass> charClasses)
    {
        this.charClasses = charClasses;
    }

    public void setAttributes(Set<PFCharacterAttribute> attributes)
    {
        this.attributes = attributes;
    }

    public void setSkillRanks(Set<PFCharacterSkill> skillRanks)
    {
        this.skillRanks = skillRanks;
    }

    // CONSTRUCTORS
    public PFCharacter ()
    {

    }
    public PFCharacter
            (
                    String name,
                    Race race,
                    Set<PFCharacterCharClass> classes,
                    Set<PFCharacterAttribute> attributes,
                    Set<PFCharacterSkill> skillRanks
            )
    {
        this.name = name;
        this.race = race;
        this.charClasses = classes;
        this.attributes = attributes;
        this.skillRanks = skillRanks;
    }
}
