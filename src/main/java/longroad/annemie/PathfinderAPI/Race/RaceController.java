package longroad.annemie.PathfinderAPI.Race;

import longroad.annemie.PathfinderAPI.Size.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/race" )
@CrossOrigin
public class RaceController
{
    // Repositories
    @Autowired
    RaceRepository raceRepo;

    // Display details of all races
    @GetMapping( "/all" )
    public ResponseEntity< List< Race > > getAllRaces()
    {
        return ResponseEntity.status(HttpStatus.OK).body(raceRepo.findAll());
    }

    public RaceController ( RaceRepository raceRepo )
    {
        this.raceRepo = raceRepo;
    }
}
