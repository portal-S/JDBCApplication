package com.portal;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.repository.DeveloperRepositoryImpl;
import com.portal.repository.SkillRepositoryImpl;
import com.portal.repository.interfaces.DeveloperRepository;
import com.portal.repository.interfaces.SkillRepository;
import com.portal.utils.Util;
import com.portal.view.View;
import liquibase.pro.packaged.A;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        view.start();
    }


}
