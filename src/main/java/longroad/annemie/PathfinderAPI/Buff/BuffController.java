package com.tsi.longroad.annemie.PathfinderAPI.Buff;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buff")
@CrossOrigin
public class BuffController
{
    BuffRepository buffRepo;

    ObjectMapper objectMapper = new ObjectMapper();

    // Display details of all buffs
    @GetMapping("/allBuffs")
    public ResponseEntity<List<Buff>> getAllBuffs()
    {
        return ResponseEntity.status(HttpStatus.OK).body(buffRepo.findAll());
    }

    // Display details of a single buff
    @GetMapping("/{buffID}")
    public Buff getBuffByID ( @PathVariable("buffID") int buffID )
    {
        try
        {
            Buff buff = buffRepo.findById(buffID)
                    .orElseThrow(() -> new BuffNotFoundException ("No buff found"));
            return buff;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    // Add a new buff
    @PostMapping("allBuffs")
    public ResponseEntity<Buff> addBuff(@RequestBody Buff buff)
    {
        Buff newBuff = buff;
        buffRepo.save(newBuff);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBuff);
    }

    // Patch buffs
    @PatchMapping(path = "/{buffID}", consumes = "application/json-patch+json")
    public ResponseEntity<Buff> updateBuff(@PathVariable int buffID,
                                           @RequestBody JsonPatch patch)
    {

        try
        {
            Buff buff = buffRepo.findById(buffID)
                    .orElseThrow(() -> new BuffNotFoundException("No buff found"));

            Buff patchedBuff = applyPatchToBuff(patch, buff);
            buffRepo.save(patchedBuff);

            return ResponseEntity.status(HttpStatus.OK).body(patchedBuff);
        }
        catch ( JsonPatchException | JsonProcessingException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        catch ( BuffNotFoundException e )
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Buff applyPatchToBuff ( JsonPatch patch, Buff targetBuff )
            throws JsonPatchException, JsonProcessingException
    {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetBuff, JsonNode.class));

        return objectMapper.treeToValue(patched, Buff.class);
    }

    // CONSTRUCTORS
    public BuffController ( BuffRepository buffRepo )
    {
        this.buffRepo = buffRepo;
    }
}
