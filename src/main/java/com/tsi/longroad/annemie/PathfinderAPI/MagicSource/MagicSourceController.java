package com.tsi.longroad.annemie.PathfinderAPI.MagicSource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/magicSource")
@CrossOrigin
public class MagicSourceController
{
    MagicSourceRepository sourceRepo;

    // Display details of all magic sources
    @GetMapping("/all")
    public ResponseEntity<List<MagicSource>> getAllMagicSources()
    {
        return ResponseEntity.status(HttpStatus.OK).body(sourceRepo.findAll());
    }

    public MagicSourceController ( MagicSourceRepository sourceRepo )
    {
        this.sourceRepo = sourceRepo;
    }
}
