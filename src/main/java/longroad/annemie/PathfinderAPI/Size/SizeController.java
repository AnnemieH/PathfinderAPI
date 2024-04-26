package longroad.annemie.PathfinderAPI.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/size")
@CrossOrigin
public class SizeController
{
    @Autowired
    SizeRepository sizeRepo;

    // Display details of all sizes
    @GetMapping("/all")
    public ResponseEntity< List<Size> > getAllSizes()
    {
        return ResponseEntity.status(HttpStatus.OK).body(sizeRepo.findAll());
    }

    public SizeController ( SizeRepository sizeRepo )
    {
        this.sizeRepo = sizeRepo;
    }
}
