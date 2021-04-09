package com.portal.view;

import com.portal.controller.DeveloperController;
import com.portal.controller.SkillController;
import com.portal.controller.TeamController;
import com.portal.model.Developer;
import liquibase.pro.packaged.S;

import java.util.Scanner;

public class View {
    public void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные");
        while (scanner.hasNext()){
            validData(scanner.nextLine());
        }
        scanner.close();
    }

    private void validData(String s){
        String[] info = s.split(" ");
        switch (info[0]){
            case "developer":
                DeveloperController developerController = new DeveloperController();
                developerController.control(s);
                break;
            case "skill":
                SkillController skillController = new SkillController();
                skillController.control(s);
                break;
            case "team":
                TeamController controller = new TeamController();
                controller.control(s);
                break;
            default:
                System.err.println("Invalid type");

        }
    }
}
