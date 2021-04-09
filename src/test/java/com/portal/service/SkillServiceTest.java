package com.portal.service;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.repository.DeveloperRepositoryImpl;
import com.portal.repository.interfaces.SkillRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

public class SkillServiceTest {

    private SkillService skillService;

    @Mock
    private SkillRepository skillRepository;

    @Before
    public void getUp(){
        MockitoAnnotations.initMocks(this);
        this.skillService = new SkillService(skillRepository);
    }


    @Test
    public void readAllTest() {
        List<Skill> skills = new ArrayList<>();
        given(skillRepository.readAll()).willReturn(skills);
        Assert.assertEquals(skillService.readAll(), skills);
    }

    @Test
    public void readTest() {
        Skill skill = new Skill(1, "TestSkill");
        given(skillRepository.read(1)).willReturn(skill);
        Assert.assertEquals(skillService.read("skill read 1"), skill);
    }

    @Test
    public void createTest() {
        Skill skill = new Skill(1, "TestSkill");
        given(skillRepository.create(skill)).willReturn(skill);
        Assert.assertEquals(skillService.create("skill create TestSkill"), skill);
    }

    @Test
    public void updateTest() {
        Skill skill = new Skill(1, "TestSkill");
        given(skillRepository.update(skill)).willReturn(skill);
        Assert.assertEquals(skillService.update("skill update 1;TestSkill"), skill);
    }
}