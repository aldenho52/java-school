package com.lambdaschool.schools.services;


import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "instructorService")
public class InstructorServicesImpl implements InstructorServices
{
    @Autowired
    private InstructorRepository instructrepos;

    @Override
    public Instructor findInstructorById(long id)
    {
        return instructrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor id " + id + " not found!"));
    }
}
