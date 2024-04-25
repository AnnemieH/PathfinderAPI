package longroad.annemie.PathfinderAPI.Save;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/save")
@CrossOrigin
public class SaveController
{
    SaveRepository saveRepo;

    // Display details of all saves
    @GetMapping("/all")
    public ResponseEntity<List<Save>> getAllSaves()
    {
        return ResponseEntity.status(HttpStatus.OK).body(saveRepo.findAll());
    }

    public SaveController ( SaveRepository saveRepo )
    {
        this.saveRepo = saveRepo;
    }
}
