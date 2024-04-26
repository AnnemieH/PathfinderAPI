package longroad.annemie.PathfinderAPI.PFCharacter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/character")
@CrossOrigin
public class PFCharacterController
{
    @Autowired
    PFCharacterRepository characterRepo;

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

    // CONSTRUCTORS
    public PFCharacterController(PFCharacterRepository characterRepo)
    {
        this.characterRepo = characterRepo;
    }
}
