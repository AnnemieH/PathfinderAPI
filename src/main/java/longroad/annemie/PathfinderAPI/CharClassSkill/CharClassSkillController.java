package longroad.annemie.PathfinderAPI.CharClassSkill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/charClassSkills")
@CrossOrigin
public class CharClassSkillController
{
    @Autowired
    CharClassSkillRepository charClassSkillRepository;

    public CharClassSkillController ( CharClassSkillRepository charClassSkillRepository )
    {
        this.charClassSkillRepository = charClassSkillRepository;
    }
}
