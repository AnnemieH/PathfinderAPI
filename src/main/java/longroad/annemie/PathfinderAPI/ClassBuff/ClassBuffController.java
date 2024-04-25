package longroad.annemie.PathfinderAPI.ClassBuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classBuff")
@CrossOrigin
public class ClassBuffController
{
    @Autowired
    ClassBuffRepository classBuffRepo;

    public ClassBuffController ( ClassBuffRepository classBuffRepo )
    {
        this.classBuffRepo = classBuffRepo;
    }
}
