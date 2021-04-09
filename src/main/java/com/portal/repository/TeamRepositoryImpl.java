package com.portal.repository;

import com.portal.model.Developer;
import com.portal.model.Skill;
import com.portal.model.Team;
import com.portal.repository.interfaces.DeveloperRepository;
import com.portal.repository.interfaces.TeamRepository;
import com.portal.utils.BDConnect;
import com.portal.utils.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamRepositoryImpl implements TeamRepository {
    @Override
    public List<Team> readAll() {
        List<Team> teams = new ArrayList<>();
        try(Connection connection = BDConnect.getConnection(); Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM teams")) {
            while (set.next()){
                teams.add(new Team(set.getInt("id"), set.getString("name"), getDevelopers(set.getInt("id"))));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public Team read(Integer integer) {
        try(Connection connection = BDConnect.getConnection(); Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM teams WHERE id = " + integer)) {
            while (set.next()){
                return new Team(set.getInt("id"), set.getString("name"), getDevelopers(set.getInt("id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Team create(Team team) {
        team.setId(Util.getMaxId("teams") + 1);
        try(Connection connection = BDConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO teams (id, name) VALUES (?, ?)")) {
            updateExecute(team, connection, statement);
            connection.commit();
            return team;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Team update(Team team) {
        try(Connection connection = BDConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE teams SET id = ? , name = ? WHERE id = " + team.getId())) {
            updateExecute(team, connection, statement);
            statement.executeUpdate("UPDATE developers SET team_id = NULL WHERE team_id = " + team.getId());
            for (Developer developer : team.getDevelopers()){
                statement.executeUpdate("UPDATE developers SET team_id = " + team.getId() + " WHERE id = " + developer.getId());
            }
            connection.commit();
            return team;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Integer integer) {
        try(Connection connection = BDConnect.getConnection();
            Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeQuery("DELETE FROM teams WHERE id = " + integer);
            statement.executeQuery("UPDATE developers SET team_id = NULL WHERE team_id = " + integer);
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private List<Developer> getDevelopers(int id){
        String sql = "SELECT id FROM developers WHERE team_id = " + id;
        DeveloperRepository repository = new DeveloperRepositoryImpl();
        List<Developer> developers = new ArrayList<>();
        try(Connection connection = BDConnect.getConnection(); Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql)) {
            while (set.next()){
                developers.add(repository.read(set.getInt("id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return developers;
    }

    private void updateExecute(Team team, Connection connection, PreparedStatement statement) throws SQLException {
        connection.setAutoCommit(false);
        statement.setInt(1, team.getId());
        statement.setString(2, team.getName());
        statement.executeUpdate();
        for(Developer developer : team.getDevelopers()){
            statement.executeUpdate("UPDATE developers SET team_id = " + team.getId() + " WHERE id = " + developer.getId());
        }
    }

}
