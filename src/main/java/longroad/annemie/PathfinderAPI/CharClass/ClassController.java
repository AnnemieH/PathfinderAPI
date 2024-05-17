package longroad.annemie.PathfinderAPI.CharClass;

import jakarta.transaction.Transactional;
import longroad.annemie.PathfinderAPI.Buff.Buff;
import longroad.annemie.PathfinderAPI.Buff.BuffRepository;
import longroad.annemie.PathfinderAPI.CharClassSkill.CharClassSkill;
import longroad.annemie.PathfinderAPI.CharClassSkill.CharClassSkillKey;
import longroad.annemie.PathfinderAPI.CharClassSkill.CharClassSkillRepository;
import longroad.annemie.PathfinderAPI.ClassBuff.ClassBuff;
import longroad.annemie.PathfinderAPI.ClassBuff.ClassBuffKey;
import longroad.annemie.PathfinderAPI.ClassBuff.ClassBuffRepository;
import longroad.annemie.PathfinderAPI.PFCharacter.PFCharacter;
import longroad.annemie.PathfinderAPI.Skill.Skill;
import longroad.annemie.PathfinderAPI.Skill.SkillRepository;
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
    BuffRepository buffRepo;
    @Autowired
    CharClassSkillRepository charClassSkillRepo;
    @Autowired
    SkillRepository skillRepo;


    // Display details of all classes
    @GetMapping("allClasses")
    public ResponseEntity<List<CharClass>> getAllClasses()
    {
        return ResponseEntity.status(HttpStatus.OK).body(classRepo.findAll());
    }

    // Post a new class
    @Transactional
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

            try
            {
                Buff buff = buffRepo.findById(key.getBuffID())
                        .orElseThrow( () -> new Exception ("No such buff found") );

                classBuff.setBuff(buff);

                classBuffRepo.save(classBuff);
            }
            catch ( Exception e )
            {
                System.out.println( e.toString() );
            }
        }

        // Add class skills to their join table
        for ( CharClassSkill classSkill : charClass.getClassSkills() )
        {
            CharClassSkillKey key = new CharClassSkillKey();
            key.setClassID(charClass.getClassID());
            key.setSkillID(classSkill.getId().getSkillID());

            classSkill.setCharClass(charClass);
            classSkill.setId(key);

            try
            {
                Skill skill = skillRepo.findById(key.getSkillID())
                        .orElseThrow( () -> new Exception ("No such skill found") );

                classSkill.setSkill(skill);
                charClassSkillRepo.save(classSkill);
            }
            catch ( Exception e )
            {
                System.out.println( e.toString() );
            }
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
