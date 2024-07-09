package longroad.annemie.PathfinderAPI.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/durations")
@CrossOrigin
public class DurationController
{
    // Repositories
    @Autowired
    DurationRepository durationRepository;

    // Display details of all durations
    @GetMapping( "/all" )
    public ResponseEntity< List< Duration> > getAllDurations()
    {
        return ResponseEntity.status(HttpStatus.OK).body(durationRepository.findAll());
    }
}
