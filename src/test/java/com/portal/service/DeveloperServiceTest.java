package com.portal.service;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.repository.DeveloperRepositoryImpl;
import com.portal.repository.interfaces.DeveloperRepository;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;

public class DeveloperServiceTest {

    private DeveloperService developerService;

    @Mock
    private DeveloperRepository developerRepository;

    @Before
    public void getUp(){
        MockitoAnnotations.initMocks(this);
        this.developerService = new DeveloperService(developerRepository);
    }


    @Test
    public void readAllTest() {
        List<Developer> developers = new ArrayList<>();
        given(developerRepository.readAll()).willReturn(developers);
        Assert.assertEquals(developerService.readAll(), developers);
    }

    @Test
    public void readTest() {
        Developer developer = new Developer(1, "Ali", "Gus", new ArrayList<>());
        given(developerRepository.read(1)).willReturn(developer);
        Assert.assertEquals(developerService.read("developer read 1"), developer);
    }

    @Test
    public void createTest() {
        Developer developer = new Developer(0, "Ali", "Gus", new ArrayList<>());
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(1, ""));
        skills.add(new Skill(2, ""));
        skills.add(new Skill(3, ""));
        developer.setSkills(skills);
        given(developerRepository.create(developer)).willReturn(developer);
        Assert.assertEquals(developerService.create("developer create Ali;Gus;1,2,3"), developer);
    }

    @Test
    public void updateTest() {
        Developer developer = new Developer(1, "Ali", "Gus", new ArrayList<>());
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(1, ""));
        skills.add(new Skill(2, ""));
        skills.add(new Skill(3, ""));
        developer.setSkills(skills);
        given(developerRepository.update(developer)).willReturn(developer);
        Assert.assertEquals(developerService.update("developer update 1;Ali;Gus;1,2,3"), developer);
    }

}