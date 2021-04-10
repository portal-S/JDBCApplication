package com.portal.repository;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.repository.interfaces.DeveloperRepository;
import com.portal.utils.BDConnect;
import com.portal.utils.Util;
import liquibase.pro.packaged.A;
import liquibase.pro.packaged.U;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public List<Developer> readAll() {
        List<Developer> developers = new ArrayList<>();
        try(Statement statement = BDConnect.getStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM developers")) {
            while (set.next()){
                developers.add(new Developer(set.getInt("id"), set.getString("firstName"),
                        set.getString("lastName"), getSkills(set.getInt("id"))));
            }
            BDConnect.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return developers;
    }

    @Override
    public Developer read(Integer integer) {
        try(Statement statement = BDConnect.getStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM developers WHERE id = " + integer)) {
            BDConnect.commit();
            while (set.next()){
                return new Developer(set.getInt("id"), set.getString("firstName"),
                        set.getString("lastName"), getSkills(set.getInt("id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Developer create(Developer developer) {
        developer.setId(Util.getMaxId("developers") + 1);
        try(PreparedStatement statement = BDConnect.getPrepareStatement("INSERT INTO developers (firstName, lastName) VALUES (?, ?)")) {
            statement.setString(1, developer.getFirstName());
            statement.setString(2, developer.getLastName());
            statement.executeUpdate();
            statement.executeUpdate("INSERT INTO developers_skills (developer_id, skill_id) VALUES " + getSkillValue(developer));
            BDConnect.commit();
            return developer;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Developer update(Developer developer) {
        try(PreparedStatement statement = BDConnect.getPrepareStatement("UPDATE developers SET firstName = ?, lastname = ? WHERE id =" + developer.getId())) {
            statement.executeUpdate("DELETE FROM developers_skills WHERE developer_id = " + developer.getId());
            statement.executeUpdate("INSERT INTO developers_skills (developer_id, skill_id) VALUES " + getSkillValue(developer));
            statement.setString(1, developer.getFirstName());
            statement.setString(2, developer.getLastName());
            statement.executeUpdate();
            BDConnect.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public void delete(Integer integer) {
        try(Statement statement = BDConnect.getStatement()) {
            statement.executeUpdate("DELETE FROM developers_skills WHERE developer_id = " + integer);
            statement.executeUpdate("DELETE FROM developers WHERE id = " + integer);
            BDConnect.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private List<Skill> getSkills(int id){
        String sql = "select Skills.id AS id, Skills.name AS name FROM developers_skills " +
                "INNER JOIN Skills ON developers_skills.skill_id = Skills.id " +
                "INNER JOIN Developers ON developers_skills.developer_id = Developers.id " +
                "WHERE developers.id = " + id;
        List<Skill> skills = new ArrayList<>();
        try(Statement statement = BDConnect.getStatement();
            ResultSet set = statement.executeQuery(sql)) {
            BDConnect.commit();
            while (set.next()){
                skills.add(new Skill(set.getInt("id"), set.getString("name")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return skills;
    }

    private String getSkillValue(Developer developer){
        StringBuilder values = new StringBuilder();
        int size = developer.getSkills().size();
        for(Skill skill : developer.getSkills()){
            values.append(" (" + developer.getId() + ", " + skill.getId() + ")");
            if(size != 1) values.append(",");
            size--;
        }
        return values.toString();
    }
}
