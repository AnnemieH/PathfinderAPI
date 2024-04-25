package longroad.annemie.PathfinderAPI.BuffType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/buffType")
@CrossOrigin
public class BuffTypeController
{
    BuffTypeRepository buffTypeRepo;

    // Display details of all buff types
    @GetMapping("/allBuffTypes")
    public ResponseEntity<List<BuffType>> getAllBuffTYpes()
    {
        return ResponseEntity.status(HttpStatus.OK).body(buffTypeRepo.findAll());
    }

    public BuffTypeController ( BuffTypeRepository buffTypeRepo )
    {
        this.buffTypeRepo = buffTypeRepo;
    }
}
