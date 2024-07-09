package longroad.annemie.PathfinderAPI.CharClassClassFeature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classClassFeature")
@CrossOrigin
public class CharClassClassFeatureController
{
    @Autowired
    CharClassClassFeatureRepository classFeatureRepository;

    public CharClassClassFeatureController(CharClassClassFeatureRepository classFeatureRepository)
    {
        this.classFeatureRepository = classFeatureRepository;
    }
}
