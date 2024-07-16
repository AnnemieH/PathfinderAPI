package longroad.annemie.PathfinderAPI.CharClass;

import jakarta.transaction.Transactional;
import longroad.annemie.PathfinderAPI.ClassFeature.ClassFeature;
import longroad.annemie.PathfinderAPI.ClassFeature.ClassFeatureRepository;
import longroad.annemie.PathfinderAPI.CharClassSkill.CharClassSkill;
import longroad.annemie.PathfinderAPI.CharClassSkill.CharClassSkillKey;
import longroad.annemie.PathfinderAPI.CharClassSkill.CharClassSkillRepository;
import longroad.annemie.PathfinderAPI.CharClassClassFeature.CharClassClassFeature;
import longroad.annemie.PathfinderAPI.CharClassClassFeature.CharClassClassFeatureKey;
import longroad.annemie.PathfinderAPI.CharClassClassFeature.CharClassClassFeatureRepository;
import longroad.annemie.PathfinderAPI.Skill.Skill;
import longroad.annemie.PathfinderAPI.Skill.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
@CrossOrigin
public class ClassController
{
    @Autowired
    ClassRepository classRepo;
    @Autowired
    CharClassClassFeatureRepository charClassClassFeatureRepository;
    @Autowired
    ClassFeatureRepository classFeatureRepository;
    @Autowired
    CharClassSkillRepository charClassSkillRepo;
    @Autowired
    SkillRepository skillRepo;


    // Display details of all classes
    @GetMapping("/all")
    public ResponseEntity<List<CharClass>> getAllClasses()
    {
        return ResponseEntity.status(HttpStatus.OK).body(classRepo.findAll());
    }

    // Post a new class
    @Transactional
    @PostMapping("all")
    public ResponseEntity<CharClass> addCharClass( @RequestBody CharClass charClass )
    {
        classRepo.save(charClass);

        // Add the class features to their join table
        for ( CharClassClassFeature charClassClassFeature : charClass.getClassFeatures() )
        {
            CharClassClassFeatureKey key = new CharClassClassFeatureKey();
            key.setClassID(charClass.getClassID());
            key.setClassFeatureID(charClassClassFeature.getId().getClassFeatureID());
            key.setLevel(charClassClassFeature.getId().getLevel());

            charClassClassFeature.setId(key);

            charClassClassFeature.setCurrClass(charClass);

            try
            {
                ClassFeature classFeature = classFeatureRepository.findById(key.getClassFeatureID())
                        .orElseThrow( () -> new Exception ("No such buff found") );

                charClassClassFeature.setClassFeature(classFeature);

                charClassClassFeatureRepository.save(charClassClassFeature);
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
