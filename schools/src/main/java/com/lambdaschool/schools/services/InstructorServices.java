package com.lambdaschool.schools.services;

import com.lambdaschool.schools.models.Instructor;

public interface InstructorServices
{
    Instructor findInstructorById(long id);
}
