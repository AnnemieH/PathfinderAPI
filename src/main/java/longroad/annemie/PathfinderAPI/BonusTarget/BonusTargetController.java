package longroad.annemie.PathfinderAPI.BonusTarget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ( "/bonusTargets" )
@CrossOrigin
public class BonusTargetController
{
    // Repositories
    @Autowired
    BonusTargetRepository bonusTargetRepository;

    // Display details of all bonus targets
    @GetMapping ( "/all" )
    public ResponseEntity < List < BonusTarget > > getAllBonusTargets()
    {
        return ResponseEntity.status(HttpStatus.OK).body(bonusTargetRepository.findAll());
    }
}
