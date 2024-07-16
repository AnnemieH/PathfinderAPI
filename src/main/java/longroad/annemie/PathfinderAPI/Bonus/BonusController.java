package longroad.annemie.PathfinderAPI.Bonus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ( "/bonuses" )
@CrossOrigin
public class BonusController
{
    // Repositories
    @Autowired
    BonusRepository bonusRepository;

    // Display details of all bonuses
    @GetMapping ( "/all" )
    public ResponseEntity < List < Bonus > > getAllBonuses()
    {
        return ResponseEntity.status(HttpStatus.OK).body(bonusRepository.findAll());
    }
}
