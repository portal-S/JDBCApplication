package com.portal.service;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.model.Team;
import com.portal.repository.interfaces.SkillRepository;
import com.portal.repository.interfaces.TeamRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

public class TeamServiceTest {

    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @Before
    public void getUp(){
        MockitoAnnotations.initMocks(this);
        this.teamService = new TeamService(teamRepository);
    }


    @Test
    public void readAllTest() {
        List<Team> teams = new ArrayList<>();
        given(teamRepository.readAll()).willReturn(teams);
        Assert.assertEquals(teamService.readAll(), teams);
    }

    @Test
    public void readTest() {
        List<Developer> developers = new ArrayList<>();
        developers.add(new Developer(1, "", "", new ArrayList<>()));
        developers.add(new Developer(2, "", "", new ArrayList<>()));
        developers.add(new Developer(3, "", "", new ArrayList<>()));
        Team team = new Team(1, "TestTeam", developers);
        given(teamRepository.read(1)).willReturn(team);
        Assert.assertEquals(teamService.read("team read 1"), team);
    }

    @Test
    public void createTest() {
        List<Developer> developers = new ArrayList<>();
        developers.add(new Developer(1, "", "", new ArrayList<>()));
        developers.add(new Developer(2, "", "", new ArrayList<>()));
        developers.add(new Developer(3, "", "", new ArrayList<>()));
        Team team = new Team(1, "TestTeam", developers);
        given(teamRepository.create(team)).willReturn(team);
        Assert.assertEquals(teamService.create("team create TestTeam;1,2,3"), team);
    }

    @Test
    public void updateTest() {
        List<Developer> developers = new ArrayList<>();
        developers.add(new Developer(1, "", "", new ArrayList<>()));
        developers.add(new Developer(2, "", "", new ArrayList<>()));
        developers.add(new Developer(3, "", "", new ArrayList<>()));
        Team team = new Team(1, "TestTeam", developers);
        given(teamRepository.update(team)).willReturn(team);
        Assert.assertEquals(teamService.update("team update 1;TestTeam;1,2,3"), team);
    }

}