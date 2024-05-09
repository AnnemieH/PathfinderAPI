package longroad.annemie.PathfinderAPI.CharClass;

import longroad.annemie.PathfinderAPI.CharClassSkill.CharClassSkill;
import longroad.annemie.PathfinderAPI.CharClassSkill.CharClassSkillKey;
import longroad.annemie.PathfinderAPI.CharClassSkill.CharClassSkillRepository;
import longroad.annemie.PathfinderAPI.ClassBuff.ClassBuff;
import longroad.annemie.PathfinderAPI.ClassBuff.ClassBuffKey;
import longroad.annemie.PathfinderAPI.ClassBuff.ClassBuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
@CrossOrigin
public class ClassController
{
    @Autowired
    ClassRepository classRepo;
    @Autowired
    ClassBuffRepository classBuffRepo;
    @Autowired
    CharClassSkillRepository charClassSkillRepo;


    // Display details of all classes
    @GetMapping("allClasses")
    public ResponseEntity<List<CharClass>> getAllClasses()
    {
        return ResponseEntity.status(HttpStatus.OK).body(classRepo.findAll());
    }

    // Post a new class
    @PostMapping("allClasses")
    public ResponseEntity<CharClass> addCharClass( @RequestBody CharClass charClass )
    {
        classRepo.save(charClass);

        // Add the buffs to their join table
        for ( ClassBuff classBuff : charClass.getBuffs() )
        {
            ClassBuffKey key = new ClassBuffKey();
            key.setClassID(charClass.getClassID());
            key.setBuffID(classBuff.getId().getBuffID());
            key.setLevel(classBuff.getId().getLevel());

            classBuff.setId(key);

            classBuff.setCurrClass(charClass);
            classBuffRepo.save(classBuff);
        }

        // Add class skills to their join table
        for ( CharClassSkill classSkill : charClass.getClassSkills() )
        {
            CharClassSkillKey key = new CharClassSkillKey();
            key.setClassID(charClass.getClassID());
            key.setSkillID(classSkill.getId().getSkillID());

            classSkill.setCharClass(charClass);
            classSkill.setId(key);
            charClassSkillRepo.save(classSkill);
        }



        return ResponseEntity.status(HttpStatus.CREATED).body(charClass);
    }

    // CONSTRUCTORS

    public ClassController ( ClassRepository classRepo)
    {
        this.classRepo = classRepo;
    }
}
