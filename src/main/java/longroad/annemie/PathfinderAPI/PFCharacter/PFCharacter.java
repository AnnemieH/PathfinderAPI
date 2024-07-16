package longroad.annemie.PathfinderAPI.PFCharacter;

import jakarta.persistence.*;
import longroad.annemie.PathfinderAPI.Attribute.Attribute;
import longroad.annemie.PathfinderAPI.CharClass.CharClass;
import longroad.annemie.PathfinderAPI.Metadata.Metadata;
import longroad.annemie.PathfinderAPI.PFCharacterAttribute.PFCharacterAttribute;
import longroad.annemie.PathfinderAPI.PFCharacterAttribute.PFCharacterAttributeKey;
import longroad.annemie.PathfinderAPI.PFCharacterCharClass.PFCharacterCharClass;
import longroad.annemie.PathfinderAPI.PFCharacterCharClass.PFCharacterCharClassKey;
import longroad.annemie.PathfinderAPI.PFCharacterSkill.PFCharacterSkill;
import longroad.annemie.PathfinderAPI.PFCharacterSkill.PFCharacterSkillKey;
import longroad.annemie.PathfinderAPI.Race.Race;
import longroad.annemie.PathfinderAPI.Skill.Skill;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @ManyToOne
    @JoinColumn ( name = "race_id" )
    private Race race;

    @OneToMany ( mappedBy = "character", orphanRemoval = true )
    private Set <PFCharacterCharClass> charClasses = new HashSet<>();

    @OneToMany( mappedBy = "character", orphanRemoval = true )
    private Set <PFCharacterAttribute> attributes = new HashSet<>();

    @OneToMany ( mappedBy = "character", orphanRemoval = true )
    private Set<PFCharacterSkill> skillRanks = new HashSet<>();

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
        setMetadata(new Metadata("/characters", true, toString()));
    }

    // MEMBER FUNCTIONS
    // Add ids to classes, attributes & skills
    public void addID( int charID )
    {
        this.characterID = charID;


        // Assign IDs for character classes
        for ( PFCharacterCharClass charClass : charClasses )
        {
            charClass.getId().setCharacterID( charID );
            charClass.setCharacter( this );
        }

        // Assign IDs for attributes
        for ( PFCharacterAttribute attribute : attributes )
        {
            attribute.getId().setCharacterID( charID );
            attribute.setCharacter( this );
        }

        // Assign IDs for skills
        for ( PFCharacterSkill skill : skillRanks )
        {
            skill.getId().setCharacterID( charID );
            skill.setCharacter( this );
        }
    }

    // Add a new class
    public void addClass ( CharClass charClass )
    {
        // Define the key
        PFCharacterCharClassKey classKey = new PFCharacterCharClassKey();
        classKey.setCharacterID( getCharacterID() );
        classKey.setClassID( charClass.getClassID() );

        // Create a new element of the join table
        PFCharacterCharClass characterCharClass = new PFCharacterCharClass();
        characterCharClass.setId(classKey);

        // Add the character and add to set of PFCharacterCharClasses
        characterCharClass.setCharacter(this);
        charClasses.add( characterCharClass );
    }

    // Remove an existing class
    public void removeClass ( CharClass charClass )
    {
        // Iterate through existing classes to see if
        // any match the one we want to remove
        for ( PFCharacterCharClass characterCharClass : charClasses )
        {
            if ( characterCharClass.getCharClass().getClassID() == charClass.getClassID() )
            {
                charClasses.remove( characterCharClass );
            }
        }
    }

    // Add a new attribute
    public void addAttribute (Attribute attribute )
    {
        // Define the key
        PFCharacterAttributeKey attributeKey = new PFCharacterAttributeKey();
        attributeKey.setAttributeID(attribute.getAttributeID());
        attributeKey.setCharacterID(getCharacterID());

        // Create a new element of the join table
        PFCharacterAttribute characterAttribute = new PFCharacterAttribute();
        characterAttribute.setId(attributeKey);

        // Add the character and add to set of PFCharacterAttributes
        characterAttribute.setCharacter( this );
        attributes.add( characterAttribute );
    }

    // Given an id, find the respective attribute. Otherwise, return null.
    public PFCharacterAttribute getCharacterAttributeByID ( short id )
    {
        for ( PFCharacterAttribute attribute : attributes )
        {
            if ( attribute.getId().getAttributeID() == id )
            {
                return attribute;
            }
        }

        return null;
    }

    // Add a new skill
    private void addSkill( Skill skill, short rank )
    {
        // Define the key
        PFCharacterSkillKey skillKey = new PFCharacterSkillKey();
        skillKey.setCharacterID( getCharacterID() );
        skillKey.setSkillID( skill.getSkillID() );

        // Create a new element of the join table
        PFCharacterSkill characterSkill = new PFCharacterSkill();
        characterSkill.setId(skillKey);

        // Add the character, skill & rank and save
        characterSkill.setCharacter( this );
        characterSkill.setSkill( skill );
        characterSkill.setRanks( rank );
        skillRanks.add( characterSkill );
    }

    // Remove an existing skill
    private void removeSkill ( Skill skill )
    {
        // Iterate through existing skills to see if
        // any match the one we want to remove
        for ( PFCharacterSkill characterSkill : skillRanks )
        {
            if ( characterSkill.getSkill().getSkillID() == skill.getSkillID() )
            {
                charClasses.remove( characterSkill );
            }
        }
    }

    // Update the current skills
    public void updateSkills ( Set<PFCharacterSkill> futureSkills )
    {
        Set<PFCharacterSkill> skillsToAdd = new HashSet<>();

        // Add all skills to be whittled down later
        skillsToAdd.addAll(futureSkills);

        // Iterate through the current skills to determine what we need to do with the skill
        for ( PFCharacterSkill characterSkill : skillRanks )
        {

            boolean toBeRemoved = true;
            
            for ( PFCharacterSkill newSkill : futureSkills )
            {
                // If characterSkill is present in futureSkills,
                // Remove it from skillsToAdd and update the ranks
                if ( characterSkill.getSkill().getSkillID() == newSkill.getSkill().getSkillID() )
                {
                    skillsToAdd.remove( newSkill );
                    characterSkill.setRanks( newSkill.getRanks() );

                    // If the skill ranks are positive, don't remove it. Otherwise do
                    if ( characterSkill.getRanks() > 0 )
                    {
                        toBeRemoved = false;
                    }
                }
            }

            // If characterSkill has been marked for removal, remove it
            if ( toBeRemoved )
            {
                removeSkill(characterSkill.getSkill());
            }
        }

        // For each skill remaining in skillsToAdd, add them
        for ( PFCharacterSkill newSkill : skillsToAdd )
        {
            addSkill( newSkill.getSkill(), newSkill.getRanks() );
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

    public Metadata getMetadata()
    {
        return metadata;
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

    public void setMetadata(Metadata metadata)
    {
        this.metadata = metadata;
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

    // Overridden methods
    @Override
    public String toString()
    {

        String output = getName();
        if ( getRace().getRaceID() == 5 && getName().equals("Terrible") )
        {
            output += " the half-human";
        }
        else
        {
            output += " the " + getRace().getRaceName();
        }

        // For every class, add them as a comma-separated list
        // replacing the comma with an ampersand for the last one
        String classesStr = " ";

        for ( PFCharacterCharClass charClass : charClasses )
        {
            if (classesStr.equals(" "))
            {
                classesStr += charClass.toString();
            }
            else
            {
                classesStr += ", " + charClass.toString();
            }
        }

        // Remove the final comma and replace it with " &" (also fuck regex)
        Pattern findFinalComma = Pattern.compile("(,)[^,]*$");
        Matcher finalClassMatch = findFinalComma.matcher(classesStr);
        finalClassMatch.find();
        classesStr = classesStr.substring(0, finalClassMatch.start()) + " &" +
                     classesStr.substring(finalClassMatch.start() + 1);


        output += classesStr;

        return output;
    }
}