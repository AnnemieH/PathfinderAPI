package longroad.annemie.PathfinderAPI.SpellList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spellLists")
@CrossOrigin
public class SpellListController
{
    SpellListRepository spellListRepo;

    // Display details of all Spell Lists
    @GetMapping("/all")
    public ResponseEntity<List<SpellList>> getAllSpellLists()
    {
        return ResponseEntity.status(HttpStatus.OK).body(spellListRepo.findAll());
    }

    public SpellListController ( SpellListRepository spellListRepo )
    {
        this.spellListRepo = spellListRepo;
    }
}
