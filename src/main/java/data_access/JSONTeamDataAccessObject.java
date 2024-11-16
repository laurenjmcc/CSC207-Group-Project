package data_access;

import entity.Team;
import use_case.team.TeamDataAccessInterface;
import utility.TeamJSONTranslator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JSONTeamDataAccessObject implements TeamDataAccessInterface {

    private final Map<String, Team> teams;
    private final File file;

    public JSONTeamDataAccessObject(String filePath) {
        this.file = new File(filePath);
        if (file.exists()) {
            teams = loadTeams();
        }
        else {
            teams = new HashMap<>();
        }
    }

    private Map<String, Team> loadTeams() {
        Map<String, Team> teams = new HashMap<>();
        try (Scanner scanner = new Scanner(file)) {
            StringBuilder jsonBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                jsonBuilder.append(scanner.nextLine());
            }
            String json = jsonBuilder.toString();
            if (!json.trim().isEmpty()) {
                String[] teamJsonArray = splitJsonArray(json);
                for (String teamJson : teamJsonArray) {
                    Team team = TeamJSONTranslator.jsonToTeam(teamJson);
                    teams.put(team.getTeamName(), team);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teams;
    }

    private void saveTeams() {
        try (FileWriter writer = new FileWriter(file)) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            int count = 0;
            for (Team team : teams.values()) {
                String teamJson = TeamJSONTranslator.teamToJson(team);
                sb.append(teamJson);
                count++;
                if (count < teams.size()) {
                    sb.append(",");
                }
            }
            sb.append("]");
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] splitJsonArray(String jsonArray) {
        String content = jsonArray.trim();
        if (content.startsWith("[") && content.endsWith("]")) {
            content = content.substring(1, content.length() - 1);
        }
        return content.split("(?<=\\}),\\s*(?=\\{)");
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
