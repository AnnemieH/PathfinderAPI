package longroad.annemie.PathfinderAPI.ClassFeature;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classFeatures")
@CrossOrigin
public class ClassFeatureController
{
    @Autowired
    ClassFeatureRepository classFeatureRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    // Display details of all class features
    @GetMapping("/all")
    public ResponseEntity<List<ClassFeature>> getAllClassFeatures()
    {
        return ResponseEntity.status(HttpStatus.OK).body(classFeatureRepository.findAll());
    }

    // Display details of a single class feature
    @GetMapping("/{classFeatureID}")
    public ClassFeature getBuffByID(@PathVariable("classFeatureID") int classFeatureID)
    {
        try
        {
            ClassFeature classFeature = classFeatureRepository.findById(classFeatureID)
                    .orElseThrow(() -> new ClassFeatureNotFoundException("No buff found"));
            return classFeature;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    // Add a new class feature
    @PostMapping("all")
    public ResponseEntity<ClassFeature> addBuff(@RequestBody ClassFeature classFeature)
    {
        ClassFeature newClassFeature = classFeature;
        classFeatureRepository.save(newClassFeature);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClassFeature);
    }

    // Patch class features
    @PatchMapping(path = "/{classFeatureID}", consumes = "application/json-patch+json")
    public ResponseEntity<ClassFeature> updateBuff(@PathVariable("classFeatureID") int classFeatureID,
                                                   @RequestBody JsonPatch patch)
    {

        try
        {
            ClassFeature classFeature = classFeatureRepository.findById(classFeatureID)
                    .orElseThrow(() -> new ClassFeatureNotFoundException("No buff found"));

            ClassFeature patchedClassFeature = applyPatchToClassFeature(patch, classFeature);
            classFeatureRepository.save(patchedClassFeature);

            return ResponseEntity.status(HttpStatus.OK).body(patchedClassFeature);
        }
        catch ( JsonPatchException | JsonProcessingException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        catch ( ClassFeatureNotFoundException e )
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private ClassFeature applyPatchToClassFeature(JsonPatch patch, ClassFeature targetClassFeature)
            throws JsonPatchException, JsonProcessingException
    {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetClassFeature, JsonNode.class));

        return objectMapper.treeToValue(patched, ClassFeature.class);
    }

    // CONSTRUCTORS
    public ClassFeatureController(ClassFeatureRepository classFeatureRepository)
    {
        this.classFeatureRepository = classFeatureRepository;
    }
}
