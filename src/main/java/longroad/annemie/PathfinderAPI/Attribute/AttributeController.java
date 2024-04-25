package longroad.annemie.PathfinderAPI.Attribute;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attribute")
@CrossOrigin
public class AttributeController
{
    AttributeRepository attributeRepo;

    // Display details of all attributes
    @GetMapping("allAttributes")
    public Iterable<Attribute> getAllAttributes()
    {
        return attributeRepo.findAll();
    }

    public AttributeController ( AttributeRepository attributeRepo )
    {
        this.attributeRepo = attributeRepo;
    }
}
