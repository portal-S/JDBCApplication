package com.portal.service;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.repository.DeveloperRepositoryImpl;
import com.portal.repository.SkillRepositoryImpl;
import com.portal.repository.interfaces.DeveloperRepository;
import com.portal.repository.interfaces.SkillRepository;

import java.util.List;

public class SkillService {

    private SkillRepository repository;

    public SkillService() {
        this.repository = new SkillRepositoryImpl<>();
    }

    public SkillService(SkillRepository repository) {
        this.repository = repository;
    }

    public List<Skill> readAll(){
        List<Skill> skills = repository.readAll();
        for (Skill skill : skills){
            System.out.println(skill);
        }
        return repository.readAll();
    }

    public Skill read(String info){
        String id = info.replace("skill read ", "");
        Skill skill = repository.read(Integer.parseInt(id));
        System.out.println(skill);
        return skill;
    }

    public Skill create(String info){
        String[] data = info.replace("skill create ", "").split(";");
        Skill skill = repository.create(new Skill(1, data[0]));
        System.out.println(skill);
        return skill;
    }

    public Skill update(String info){
        String[] data = info.replace("skill update ", "").split(";");
        Skill skill = repository.update(new Skill(Integer.parseInt(data[0]), data[1]));
        System.out.println(skill);
        return skill;
    }

    public void delete(String info){
        String id = info.replace("skill delete ", "");
        repository.delete(Integer.parseInt(id));
        System.out.println(id +  " deleted!");
    }
}
