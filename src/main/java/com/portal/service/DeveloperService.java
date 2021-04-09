package com.portal.service;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.repository.DeveloperRepositoryImpl;
import com.portal.repository.interfaces.DeveloperRepository;
import liquibase.pro.packaged.D;

import java.util.ArrayList;
import java.util.List;

public class DeveloperService {

    private DeveloperRepository repository;

    public DeveloperService() {
        this.repository = new DeveloperRepositoryImpl();
    }

    public DeveloperService(DeveloperRepository repository) {
        this.repository = repository;
    }

    public List<Developer> readAll(){
        List<Developer> developers = repository.readAll();
        for (Developer developer : developers){
            System.out.println(developer);
        }
        return developers;
    }

    public Developer read(String info){
        String id = info.replace("developer read ", "");
        Developer developer = repository.read(Integer.parseInt(id));
        System.out.println(developer);
        return developer;
    }

    public Developer create(String info){
        String[] data = info.replace("developer create ", "").split(";");
        List<Skill> skills = new ArrayList<>();
        for (String s : data[2].split(",")){
            skills.add(new Skill(Integer.parseInt(s), ""));
        }
        Developer developer = repository.create(new Developer(0, data[0], data[1], skills));
        System.out.println(developer);
        return developer;
    }

    public Developer update(String info){
        String[] data = info.replace("developer update ", "").split(";");
        List<Skill> skills = new ArrayList<>();
        for (String s : data[3].split(",")){
            skills.add(new Skill(Integer.parseInt(s), ""));
        }
        Developer developer = repository.update(new Developer(Integer.parseInt(data[0]), data[1] , data[2], skills));
        System.out.println(developer);
        return developer;
    }

    public void delete(String info){
        String id = info.replace("developer delete ", "");
        repository.delete(Integer.parseInt(id));
        System.out.println(id + " deleted!");
    }
}
