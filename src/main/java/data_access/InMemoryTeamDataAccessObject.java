package data_access;

import entity.Team;
import use_case.team.TeamDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTeamDataAccessObject implements TeamDataAccessInterface {
    private final Map<String, Team> teams = new HashMap<>();

    @Override
    public void saveTeam(Team team) {
        teams.put(team.getTeamName(), team);
    }

    @Override
    public Team getTeam(String teamName) {
        return teams.get(teamName);
    }

    @Override
    public boolean teamExists(String teamName) {
        return teams.containsKey(teamName);
    }
}
