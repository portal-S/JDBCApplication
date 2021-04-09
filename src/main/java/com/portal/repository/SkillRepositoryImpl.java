package com.portal.repository;

import com.portal.model.Skill;
import com.portal.repository.interfaces.SkillRepository;
import com.portal.utils.BDConnect;
import com.portal.utils.RepoInterface;
import com.portal.utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillRepositoryImpl<T> implements SkillRepository {


    @Override
    public List<Skill> readAll(){
        List<Skill> skills = new ArrayList<>();
        func((connection, set, statement) -> {
            set = statement.executeQuery("SELECT * FROM skills");
            while (set.next()){
                skills.add(new Skill(set.getInt("id"), set.getString("name")));
            }
            return skills;
        });
        return skills;
    }

    @Override
    public Skill read(Integer integer) {
        return (Skill) func((connection, set, statement) -> {
            set = statement.executeQuery("SELECT * FROM skills WHERE id = " + integer);
            if (set.next()){
                return new Skill(set.getInt("id"), set.getString("name"));
            }
            return null;
        });
    }

    @Override
    public Skill create(Skill skill){
            return (Skill) func((con, set, stat) -> {
                skill.setId(Util.getMaxId("skills") + 1);
                stat.execute("INSERT INTO skills (id, name) VALUES (" + skill.getId() + ", '" + skill.getName() + "')");
                return skill;
            });
    }

    @Override
    public Skill update(Skill skill) {
        return (Skill) func((con, set, stat) -> {
            stat.execute("UPDATE skills SET name = '" + skill.getName() + "' WHERE id = " + skill.getId());
            return skill;
        });
    }

    @Override
    public void delete(Integer id) {
        func((con, set, stat) -> {
            stat.execute("DELETE FROM developers_skills WHERE skill_id = " + id);
            stat.execute("DELETE FROM skills WHERE id = " + id);
            return null;
        });
    }

    private T func(RepoInterface repoInterface){
        ResultSet set = null;
        T t = null;
            try(Connection connection = BDConnect.getConnection(); Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                t = (T) repoInterface.func(connection, set, statement);
                connection.commit();
                if(set != null) set.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        return t;
    }
}
