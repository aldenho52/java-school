package com.lambdaschool.schools.controllers;


import com.lambdaschool.schools.models.AdviceSlip;
import com.lambdaschool.schools.models.AdviceSlipWithQuery;
import com.lambdaschool.schools.models.Instructor;
import com.lambdaschool.schools.models.Slip;
import com.lambdaschool.schools.repositories.InstructorRepository;
import com.lambdaschool.schools.services.InstructorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping("/instructors")
public class InstructorController
{

    @Autowired
    private InstructorServices instructorServices;


    @Autowired
    private InstructorRepository instructrepos;

    // /instructors/instructor/{instructorid}/advice
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/instructor/{instructorid}/advice", produces = "application/json")
    public ResponseEntity<?> getInstructorWithAdvice(@PathVariable long instructorid)
    {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        String requestURL = "https://api.adviceslip.com/advice";

        ParameterizedTypeReference<AdviceSlip> responseType = new ParameterizedTypeReference<>()
        {
        };

        ResponseEntity<AdviceSlip> responseEntity = restTemplate.exchange(requestURL, HttpMethod.GET, null, responseType);

        Slip slip = responseEntity.getBody().getSlip();
        Instructor instructor = instructorServices.findInstructorById(instructorid);
        instructor.setAdvice(slip.getAdvice());

        return new ResponseEntity<>(instructor, HttpStatus.OK);

    }


    // /instructors/instructor/{instructorid}/advice/{search term}
    @GetMapping(value = "/instructor/{instructorid}/advice/{searchterm}", produces = "application/json")
    public ResponseEntity<?> getInstructorWithAdviceBySearchTerm(@PathVariable long instructorid, @PathVariable String searchterm)
    {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        String requestURL = "https://api.adviceslip.com/advice/search/" + searchterm;

        ParameterizedTypeReference<AdviceSlipWithQuery> responseType = new ParameterizedTypeReference<>()
        {
        };

        ResponseEntity<AdviceSlipWithQuery> responseEntity = restTemplate.exchange(requestURL, HttpMethod.GET, null, responseType);

        Slip slip = responseEntity.getBody().getSlips()
            .get(0);
        Instructor instructor = instructorServices.findInstructorById(instructorid);
        instructor.setAdvice(slip.getAdvice());

        return new ResponseEntity<>(instructor, HttpStatus.OK);

    }
}
