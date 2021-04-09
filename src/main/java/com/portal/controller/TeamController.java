package com.portal.controller;

import com.portal.service.DeveloperService;
import com.portal.service.TeamService;

public class TeamController {

    private TeamService service = new TeamService();

    public void control(String info){
        String[] infos = info.split(" ");
        switch (infos[1]){
            case "readAll":
                readAll();
                break;
            case "read":
                read(info);
                break;
            case "create":
                create(info);
                break;
            case "update":
                update(info);
                break;
            case "delete":
                delete(info);
                break;
            default:
                System.err.println("Invalid data");
        }
    }

    private void readAll(){
        service.readAll();
    }

    private void read(String info){
        service.read(info);
    }

    private void create(String info){
        service.create(info);
    }

    private void update(String info){
        service.update(info);
    }

    private void delete(String info){
        service.delete(info);
    }
}
