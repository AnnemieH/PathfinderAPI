package longroad.annemie.PathfinderAPI.ClassFeatureBonus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ( "/classFeatureBonuses" )
@CrossOrigin
public class ClassFeatureBonusController
{
    // Repositories
    @Autowired
    ClassFeatureBonusRepository classFeatureBonusRepository;

    // Display details of all ClassFeatureBonuses
    @GetMapping ( "/all" )
    public ResponseEntity < List < ClassFeatureBonus > > getAllClassFeatureBonuses()
    {
        return ResponseEntity.status(HttpStatus.OK).body(classFeatureBonusRepository.findAll());
    }
}
