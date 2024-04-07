package com.tsi.longroad.annemie.PathfinderAPI.Class;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/class")
@CrossOrigin
public class ClassController
{
    ClassRepository classRepo;

    // Display details of all classes
    @GetMapping("allClasses")
    public Iterable<CharClass> getAllClasses()
    {
        return classRepo.findAll();
    }

    public ClassController ( ClassRepository classRepo)
    {
        this.classRepo = classRepo;
    }
}
