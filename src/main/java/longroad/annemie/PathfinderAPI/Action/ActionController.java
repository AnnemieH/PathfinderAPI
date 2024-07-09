package longroad.annemie.PathfinderAPI.Action;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/action")
@CrossOrigin
public class ActionController
{
    ActionRepository actionRepo;

    // Display details of all times
    @GetMapping("/all")
    public ResponseEntity<List<Action>> getAllAction()
    {
        return ResponseEntity.status(HttpStatus.OK).body(actionRepo.findAll());
    }

    public ActionController(ActionRepository actionRepo)
    {
        this.actionRepo = actionRepo;
    }
}
