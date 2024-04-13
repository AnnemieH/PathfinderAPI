package com.tsi.longroad.annemie.PathfinderAPI.Time;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/time")
@CrossOrigin
public class TimeController
{
    TimeRepository timeRepo;

    // Display details of all times
    @GetMapping("/all")
    public ResponseEntity<List<Time>> getAllTime()
    {
        return ResponseEntity.status(HttpStatus.OK).body(timeRepo.findAll());
    }

    public TimeController( TimeRepository timeRepo )
    {
        this.timeRepo = timeRepo;
    }
}
