package longroad.annemie.PathfinderAPI.Spell;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spells")
@CrossOrigin
public class SpellController
{
    SpellRepository spellRepo;

    // Display details of all spells
    @GetMapping("/all")
    public ResponseEntity<List<Spell>> getAllSpells()
    {
        return ResponseEntity.status(HttpStatus.OK).body(spellRepo.findAll());
    }

    public SpellController ( SpellRepository spellRepo )
    {
        this.spellRepo = spellRepo;
    }
}
