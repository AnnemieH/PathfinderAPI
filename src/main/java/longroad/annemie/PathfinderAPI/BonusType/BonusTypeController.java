package longroad.annemie.PathfinderAPI.BonusType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/bonusTypes" )
@CrossOrigin
public class BonusTypeController
{
    // Repositories
    @Autowired
    BonusTypeRepository bonusTypeRepository;

    // Display details of all bonus types
    @GetMapping( "/all" )
    public ResponseEntity<List<BonusType>> getAllBonusTypes ()
    {
        return ResponseEntity.status(HttpStatus.OK).body(bonusTypeRepository.findAll());
    }
}
