package longroad.annemie.PathfinderAPI.PFCharacter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import longroad.annemie.PathfinderAPI.CharClass.CharClass;
import longroad.annemie.PathfinderAPI.CharClass.ClassRepository;
import longroad.annemie.PathfinderAPI.PFCharacterAttribute.PFCharacterAttribute;
import longroad.annemie.PathfinderAPI.PFCharacterAttribute.PFCharacterAttributeKey;
import longroad.annemie.PathfinderAPI.PFCharacterAttribute.PFCharacterAttributeRepository;
import longroad.annemie.PathfinderAPI.PFCharacterCharClass.PFCharacterCharClass;
import longroad.annemie.PathfinderAPI.PFCharacterCharClass.PFCharacterCharClassKey;
import longroad.annemie.PathfinderAPI.PFCharacterCharClass.PFCharacterCharClassRepository;
import longroad.annemie.PathfinderAPI.PFCharacterSkill.PFCharacterSkill;
import longroad.annemie.PathfinderAPI.PFCharacterSkill.PFCharacterSkillKey;
import longroad.annemie.PathfinderAPI.PFCharacterSkill.PFCharacterSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;

@RestController
@RequestMapping("/character")
@CrossOrigin
public class PFCharacterController
{
    @Autowired
    PFCharacterRepository characterRepo;

    @Autowired
    PFCharacterCharClassRepository classRepository;
    @Autowired
    PFCharacterAttributeRepository attributeRepo;
    @Autowired
    PFCharacterSkillRepository skillRepo;

    ObjectMapper objectMapper = new ObjectMapper();



    // Display details of all characters
    @GetMapping ( "/all" )
    public ResponseEntity< List<PFCharacter> > getAllCharacters()
    {
        return ResponseEntity.status(HttpStatus.OK).body(characterRepo.findAll());
    }

    // Display details of a specific character
    @GetMapping ( "/{charID}" )
    public ResponseEntity<PFCharacter> getCharacterByID(@PathVariable( "charID" ) int charID)
    {
        try
        {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(characterRepo.findById(charID)
                                  .orElseThrow(() -> new Exception("Not found")));
        }
        catch ( Exception e )
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PFCharacter());
        }
    }

    // Add every class
    public void addClasses( PFCharacter pfChar, Set<PFCharacterCharClass> classes)
    {
        for ( PFCharacterCharClass charClass : classes )
        {
            // Define the key
            PFCharacterCharClassKey classKey = new PFCharacterCharClassKey();
            classKey.setCharacterID(pfChar.getCharacterID());
            classKey.setClassID(charClass.getCharClass().getClassID());
            charClass.setId(classKey);

            // Add the character and save
            charClass.setCharacter(pfChar);
            classRepository.save(charClass);
        }
    }

    // Add every attribute
    public void addAttributes ( PFCharacter pfChar, Set<PFCharacterAttribute> attributes )
    {
        for ( PFCharacterAttribute attribute : attributes )
        {
            // Define the key
            PFCharacterAttributeKey attributeKey = new PFCharacterAttributeKey();
            attributeKey.setAttributeID(attribute.getAttribute().getAttributeID());
            attributeKey.setCharacterID(pfChar.getCharacterID());
            attribute.setId(attributeKey);

            // Add the character and save
            attribute.setCharacter(pfChar);

            attributeRepo.save(attribute);
        }
    }

    // Add every skill
    public void addSkills( PFCharacter pfChar, Set<PFCharacterSkill> skills)
    {
        for ( PFCharacterSkill skill : skills )
        {
            // Define the key
            PFCharacterSkillKey skillKey = new PFCharacterSkillKey();
            skillKey.setCharacterID(pfChar.getCharacterID());
            skillKey.setSkillID(skill.getSkill().getSkillID());
            skill.setId(skillKey);

            // Add the character and save
            skill.setCharacter(pfChar);
            skillRepo.save(skill);
        }
    }


    // Add a new character
    @PostMapping
    public ResponseEntity <PFCharacter> addChar ( @RequestBody PFCharacter pfCharacter )
    {
        // Create a skeleton character
        PFCharacter bareChar = new PFCharacter();
        bareChar.setName(pfCharacter.getName());
        bareChar.setRace(pfCharacter.getRace());
        characterRepo.save(bareChar);

        // Add attributes to the skeleton
        addAttributes( bareChar, pfCharacter.getAttributes() );

        // Add classes to the skeleton
        addClasses(bareChar, pfCharacter.getCharClasses());
        //bareChar.setCharClasses(pfCharacter.getCharClasses());

        // Add skills to the skeleton
        addSkills(bareChar, pfCharacter.getSkillRanks());
        //bareChar.setSkillRanks(pfCharacter.getSkillRanks());

        return ResponseEntity.status( HttpStatus.CREATED ).body(bareChar);
    }

    // Patch characters
    @Transactional
    @PatchMapping( path = "/{charID}", consumes = "application/json-patch+json" )
    public ResponseEntity <PFCharacter> updateChar(@PathVariable int charID,
                                             @RequestBody Map< String, Object > patch)
    {
        try
        {
            PFCharacter pfChar = characterRepo.findById( charID )
                    .orElseThrow( () -> new Exception ("No such character found") );

            pfChar = applyPatchToCharacter( patch, pfChar );

            characterRepo.save(pfChar);

            return ResponseEntity.status( HttpStatus.OK ).body( pfChar );
        }
        catch ( Exception e )
        {
            System.out.println( e.toString() );
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).build();
        }
    }

    private PFCharacter applyPatchToCharacter ( Map< String, Object > patch,
                                                PFCharacter targetChar )
    {
        patch.forEach((key, value) ->
                      {
                          Field field = ReflectionUtils.findField(PFCharacter.class, key);
                          field.setAccessible(true);

                          if ( key == "skillRanks" )
                          {
                              Set<?> skillSet = objectMapper.convertValue(value, Set.class);

                              Set < PFCharacterSkill > finalSkillSet = new HashSet<>();

                              for ( Object skill : skillSet )
                              {
                                  try
                                  {
                                      // If skill already exists, grab it from the relevant repository
                                      PFCharacterSkill castSkill = objectMapper.convertValue(skill, PFCharacterSkill.class);
                                      PFCharacterSkill foundSkill = skillRepo.findById(castSkill.getId())
                                              .orElseThrow(() -> new Exception("Skill not found"));

                                      finalSkillSet.add(foundSkill);
                                  } catch (Exception e)
                                  {
                                      System.err.println(e.toString());
                                  }
                              }


                              ReflectionUtils.setField( field, targetChar, finalSkillSet );
                          }
                          else
                          {
                              ReflectionUtils.setField( field, targetChar, value );
                          }

                      }
                     );
        return targetChar;
    }

    // CONSTRUCTORS
    public PFCharacterController(PFCharacterRepository characterRepo)
    {
        this.characterRepo = characterRepo;
    }
}
