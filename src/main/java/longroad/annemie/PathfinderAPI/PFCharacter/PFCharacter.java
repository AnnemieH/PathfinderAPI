package longroad.annemie.PathfinderAPI.PFCharacter;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.PFCharacterAttribute.PFCharacterAttribute;
import longroad.annemie.PathfinderAPI.PFCharacterCharClass.PFCharacterCharClass;
import longroad.annemie.PathfinderAPI.PFCharacterSkill.PFCharacterSkill;
import longroad.annemie.PathfinderAPI.Race.Race;

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

    @OneToMany ( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "Character_character_id" )
    private Set <PFCharacterCharClass> charClasses;

    @OneToMany ( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "character_id" )
    private Set <PFCharacterAttribute> attributes;

    @OneToMany ( cascade = CascadeType.MERGE )
    @JoinColumn ( name = "character_id" )
    private Set <PFCharacterSkill> skillRanks;


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
}
