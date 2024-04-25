package longroad.annemie.PathfinderAPI.SpellcasterType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spellcasterTypes")
@CrossOrigin
public class SpellcasterTypeController
{
    SpellcasterTypeRepository typeRepo;

    @GetMapping("/allSpellcasterTypes")
    public ResponseEntity<List<SpellcasterType>> getAllSpellcasterTypes()
    {
        return ResponseEntity.status(HttpStatus.OK).body((typeRepo.findAll()));
    }

    // CONSTRUCTORS
    public SpellcasterTypeController ( SpellcasterTypeRepository typeRepo )
    {
        this.typeRepo = typeRepo;
    }
}
