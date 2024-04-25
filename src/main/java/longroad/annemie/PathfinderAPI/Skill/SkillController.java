package longroad.annemie.PathfinderAPI.Skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
@CrossOrigin
public class SkillController
{
    @Autowired
    SkillRepository skillRepo;

    // Display details of all classes
    @GetMapping("allSkills")
    public Iterable<Skill> getAllSkills()
    {
        return skillRepo.findAll();
    }

    public SkillController (SkillRepository skillRepo)
    {
        this.skillRepo = skillRepo;
    }
}
