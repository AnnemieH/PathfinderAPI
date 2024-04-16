package com.tsi.longroad.annemie.PathfinderAPI.SpellRange;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spellRange")
@CrossOrigin
public class SpellRangeController
{
    SpellRangeRepository rangeRepo;

    // Display details of all spell ranges
    @GetMapping("/all")
    public ResponseEntity<List<SpellRange>> getAllSpellRanges()
    {
        return ResponseEntity.status(HttpStatus.OK).body(rangeRepo.findAll());
    }

    public SpellRangeController ( SpellRangeRepository rangeRepo )
    {
        this.rangeRepo = rangeRepo;
    }
}
