package longroad.annemie.PathfinderAPI.Time;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/times" )
@CrossOrigin
public class TimeController
{
    @Autowired
    TimeRepository timeRepository;

    // Display details of all times
    @GetMapping( "/all" )
    public ResponseEntity< List< Time > > getAllTimes()
    {
        return ResponseEntity.status(HttpStatus.OK ).body(timeRepository.findAll());
    }

}
