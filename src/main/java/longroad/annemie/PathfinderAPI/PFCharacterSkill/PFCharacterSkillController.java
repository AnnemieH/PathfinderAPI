package longroad.annemie.PathfinderAPI.PFCharacterSkill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/characterSkill")
@CrossOrigin
public class PFCharacterSkillController
{
    @Autowired
    PFCharacterSkillRepository charSkillRepo;

    // Get details of a single PFCharacterSkill
    public PFCharacterSkill getCharacterSkillByID( PFCharacterSkillKey id )
    {
        try
        {
            PFCharacterSkill charSkill = charSkillRepo.findById( id )
                    .orElseThrow( () -> new Exception( "PFCharacterSkill not found" ) );

            return charSkill;
        }
        catch ( Exception e )
        {
            System.err.println( e.toString() );
            return null;
        }
    }

    @PostMapping("/all")
    public ResponseEntity<PFCharacterSkill> addCharacterSkills( @RequestBody PFCharacterSkill characterSkill )
    {
        charSkillRepo.save( characterSkill );
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSkill);
    }

    @PutMapping
    public ResponseEntity< PFCharacterSkill > replacePFCharacterSkill
            ( PFCharacterSkill charSkill )
    {

        charSkillRepo.save(charSkill);

        return ResponseEntity.status(HttpStatus.OK).body( charSkill );
    }

    // CONSTRUCTORS
    public PFCharacterSkillController ( PFCharacterSkillRepository charSkillRepo )
    {
        this.charSkillRepo = charSkillRepo;
    }
}
