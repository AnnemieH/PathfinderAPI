package longroad.annemie.PathfinderAPI.CreatureType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/creatureTypes")
@CrossOrigin
public class CreatureTypeController
{
    @Autowired
    CreatureTypeRepository creatureTypeRepo;

    // Display details of all creature types
    @GetMapping("/all")
    public ResponseEntity < List<CreatureType> > getAllCreatureTypes()
    {
        return ResponseEntity.status(HttpStatus.OK).body(creatureTypeRepo.findAll());
    }

    // CONSTRUCTORS
    public CreatureTypeController ( CreatureTypeRepository creatureTypeRepo )
    {
        this.creatureTypeRepo = creatureTypeRepo;
    }
}
