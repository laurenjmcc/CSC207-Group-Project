package data_access;

import entity.Team;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.team.TeamDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JSONTeamDataAccessObject implements TeamDataAccessInterface {

    private final Map<String, Team> teams;
    private final File file;

    public JSONTeamDataAccessObject(String filePath) {
        this.file = new File(filePath);
        if (file.exists()) {
            teams = loadTeams();
        } else {
            teams = new HashMap<>();
            saveTeams();
        }
    }

    private Map<String, Team> loadTeams() {
        Map<String, Team> teams = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String jsonData = jsonBuilder.toString();
            if (!jsonData.isEmpty()) {
                JSONArray teamsArray = new JSONArray(jsonData);
                for (int i = 0; i < teamsArray.length(); i++) {
                    JSONObject teamObject = teamsArray.getJSONObject(i);
                    String teamName = teamObject.getString("teamName");
                    JSONArray membersArray = teamObject.getJSONArray("memberUsernames");
                    Set<String> memberUsernames = new HashSet<>();
                    for (int j = 0; j < membersArray.length(); j++) {
                        memberUsernames.add(membersArray.getString(j));
                    }
                    Team team = new Team(teamName, memberUsernames);
                    teams.put(teamName, team);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading team data from file", e);
        }
        return teams;
    }

    private void saveTeams() {
        JSONArray teamsArray = new JSONArray();
        for (Team team : teams.values()) {
            JSONObject teamObject = new JSONObject();
            teamObject.put("teamName", team.getTeamName());
            JSONArray membersArray = new JSONArray(team.getMemberUsernames());
            teamObject.put("memberUsernames", membersArray);
            teamsArray.put(teamObject);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(teamsArray.toString(2));
        } catch (IOException e) {
            throw new RuntimeException("Error saving team data", e);
        }
    }

    @Override
    public void saveTeam(Team team) {
        teams.put(team.getTeamName(), team);
        saveTeams();
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