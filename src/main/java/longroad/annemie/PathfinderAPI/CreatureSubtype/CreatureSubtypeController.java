package longroad.annemie.PathfinderAPI.CreatureSubtype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/creatureSubtypes")
@CrossOrigin
public class CreatureSubtypeController
{
    @Autowired
    CreatureSubtypeRepository creatureSubtypeRepo;

    // Display details of all subclasses
    @GetMapping("/all")
    public ResponseEntity<List<CreatureSubtype>> getAllSubtypes()
    {
        return ResponseEntity.status(HttpStatus.OK).body(creatureSubtypeRepo.findAll());
    }

    // CONSTRUCTORS
    public CreatureSubtypeController(CreatureSubtypeRepository creatureSubtypeRepo)
    {
        this.creatureSubtypeRepo = creatureSubtypeRepo;
    }
}
