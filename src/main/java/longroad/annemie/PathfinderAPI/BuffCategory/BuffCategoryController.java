package longroad.annemie.PathfinderAPI.BuffCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/buffCategories" )
@CrossOrigin
public class BuffCategoryController
{
    // Repositories
    @Autowired
    BuffCategoryRepository buffCategoryRepository;

    // Display details of all buffCategories
    @GetMapping ( "/all" )
    public ResponseEntity<List<BuffCategory>> getAllBuffCategories ()
    {
        return ResponseEntity.status(HttpStatus.OK).body(buffCategoryRepository.findAll());
    }
}
