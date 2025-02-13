package longroad.annemie.PathfinderAPI.SpellSchool;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spellSchools")
public class SpellSchoolController
{
    SpellSchoolRepository schoolRepo;

    // Display details of all spell schools
    @GetMapping("/all")
    public ResponseEntity<List<SpellSchool>> getAllSchools ()
    {
        return ResponseEntity.status(HttpStatus.OK).body(schoolRepo.findAll());
    }

    public SpellSchoolController ( SpellSchoolRepository schoolRepo )
    {
        this.schoolRepo = schoolRepo;
    }

}
