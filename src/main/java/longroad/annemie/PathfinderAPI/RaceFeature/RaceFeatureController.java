package longroad.annemie.PathfinderAPI.RaceFeature;

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
@RequestMapping("/raceFeatures")
@CrossOrigin
public class RaceFeatureController
{
    @Autowired
    RaceFeatureRepository raceFeatureRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    // Display details of all class features
    @GetMapping("/all")
    public ResponseEntity<List<RaceFeature>> getAllClassFeatures()
    {
        return ResponseEntity.status(HttpStatus.OK).body(raceFeatureRepository.findAll());
    }

    // Display details of a single class feature
    @GetMapping("/{raceFeatureID}")
    public RaceFeature getBuffByID(@PathVariable("raceFeatureID") int raceFeatureID)
    {
        try
        {
            RaceFeature raceFeature = raceFeatureRepository.findById(raceFeatureID)
                    .orElseThrow(() -> new RaceFeatureNotFoundException("No buff found"));
            return raceFeature;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    // Add a new class feature
    @PostMapping("all")
    public ResponseEntity<RaceFeature> addBuff(@RequestBody RaceFeature raceFeature)
    {
        RaceFeature newRaceFeature = raceFeature;
        raceFeatureRepository.save(newRaceFeature);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRaceFeature);
    }

    // Patch class features
    @PatchMapping(path = "/{raceFeatureID}", consumes = "application/json-patch+json")
    public ResponseEntity<RaceFeature> updateBuff(@PathVariable("raceFeatureID") int raceFeatureID,
                                                  @RequestBody JsonPatch patch)
    {

        try
        {
            RaceFeature raceFeature = raceFeatureRepository.findById(raceFeatureID)
                    .orElseThrow(() -> new RaceFeatureNotFoundException("No buff found"));

            RaceFeature patchedRaceFeature = applyPatchToRaceFeature(patch, raceFeature);
            raceFeatureRepository.save(patchedRaceFeature);

            return ResponseEntity.status(HttpStatus.OK).body(patchedRaceFeature);
        }
        catch ( JsonPatchException | JsonProcessingException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        catch ( RaceFeatureNotFoundException e )
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private RaceFeature applyPatchToRaceFeature(JsonPatch patch, RaceFeature targetRaceFeature)
            throws JsonPatchException, JsonProcessingException
    {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetRaceFeature, JsonNode.class));

        return objectMapper.treeToValue(patched, RaceFeature.class);
    }

    // CONSTRUCTORS
    public RaceFeatureController(RaceFeatureRepository raceFeatureRepository)
    {
        this.raceFeatureRepository = raceFeatureRepository;
    }
}
