package com.portal.service;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.model.Team;
import com.portal.repository.DeveloperRepositoryImpl;
import com.portal.repository.TeamRepositoryImpl;
import com.portal.repository.interfaces.DeveloperRepository;
import com.portal.repository.interfaces.TeamRepository;

import java.util.ArrayList;
import java.util.List;

public class TeamService {

    private TeamRepository repository;

    public TeamService() {
        this.repository = new TeamRepositoryImpl();
    }

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> readAll(){
        List<Team> teams = repository.readAll();
        for (Team team : teams){
            System.out.println(team);
        }
        return teams;
    }

    public Team read(String info){
        String id = info.replace("team read ", "");
        Team team = repository.read(Integer.parseInt(id));
        System.out.println(team);
        return team;
    }

    public Team create(String info){
        String[] data = info.replace("team create ", "").split(";");
        List<Developer> developers = new ArrayList<>();
        for (String s : data[1].split(",")){
            developers.add(new Developer(Integer.parseInt(s), "", "", new ArrayList<>()));
        }
        Team team = repository.create(new Team(1, data[0], developers));
        System.out.println(team);
        return team;
    }

    public Team update(String info){
        String[] data = info.replace("team update ", "").split(";");
        List<Developer> developers = new ArrayList<>();
        for (String s : data[2].split(",")){
            developers.add(new Developer(Integer.parseInt(s), "", "", new ArrayList<>()));
        }
        Team team = repository.update(new Team(Integer.parseInt(data[0]), data[1], developers));
        System.out.println(team);
        return team;
    }

    public void delete(String info){
        String id = info.replace("team delete ", "");
        repository.delete(Integer.parseInt(id));
        System.out.println(id + " deleted!");
    }

}
