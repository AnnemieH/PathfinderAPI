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


    // Add a new character
    @Transactional
    @PostMapping
    public ResponseEntity <PFCharacter> addChar ( @RequestBody PFCharacter pfCharacter )
    {
        // Add the raw character
        characterRepo.save( pfCharacter );

        pfCharacter.addID( pfCharacter.getCharacterID() );


        // In turn, add classes, attributes & skills to their respective join tables
        for ( PFCharacterCharClass charClass : pfCharacter.getCharClasses() )
        {
            classRepository.save( charClass );
        }

        for ( PFCharacterAttribute attribute : pfCharacter.getAttributes() )
        {
            attributeRepo.save( attribute );
        }

        for ( PFCharacterSkill skill : pfCharacter.getSkillRanks() )
        {
            skillRepo.save( skill );
        }

        return ResponseEntity.status( HttpStatus.CREATED ).body(pfCharacter);
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
                          if ( key == "skillRanks" )
                          {
                              Set<?> skillSet = objectMapper.convertValue(value, Set.class);

                              Set < PFCharacterSkill > finalSkillSet = new HashSet<>();

                              // Cast all the new skills into finalSkillSet
                              for ( Object skill : skillSet )
                              {
                                  PFCharacterSkill castSkill = objectMapper.convertValue(skill, PFCharacterSkill.class);
                                  castSkill.setCharacter( targetChar );

                                  finalSkillSet.add(castSkill);
                              }

                              targetChar.updateSkills( finalSkillSet );

                              // Iterate through the skills. If the rank is positive, save it. Otherwise, remove it
                              for ( PFCharacterSkill skill : targetChar.getSkillRanks() )
                              {
                                  if ( skill.getRanks() > 0 )
                                  {
                                      skillRepo.save(skill);
                                  }
                                  else
                                  {
                                      skillRepo.deleteById( skill.getId() );
                                  }
                              }

                          }
                          else if ( key == "attributes" )
                          {
                              Set<?> attributeSet = objectMapper.convertValue(value, Set.class);

                              // For each attribute we wish to modify, update the correct element and save it
                              for ( Object attribute : attributeSet )
                              {
                                  PFCharacterAttribute castAttribute = objectMapper.convertValue(attribute, PFCharacterAttribute.class);

                                  PFCharacterAttribute target = targetChar.getCharacterAttributeByID( castAttribute.getId().getAttributeID() );
                                  target.setValue(castAttribute.getValue() );

                                  attributeRepo.save( target );
                              }
                          }
                          else if ( key == "charClasses" )
                          {
                              Set<?> classesSet = objectMapper.convertValue(value, Set.class);

                              // For each class we wish to modify, update the correct element and save it
                              for ( Object charClass : classesSet )
                              {
                                  PFCharacterCharClass castClass = objectMapper.convertValue( charClass, PFCharacterCharClass.class );
                                  castClass.setCharacter( targetChar );
                                  castClass.getId().setCharacterID( targetChar.getCharacterID() );

                                  classRepository.save(castClass);
                              }
                          }
                          else
                          {
                              Field field = ReflectionUtils.findField(PFCharacter.class, key);
                              field.setAccessible(true);
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
