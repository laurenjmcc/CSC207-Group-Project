package utility;

import entity.Team;
import java.util.Set;
import java.util.HashSet;

public class TeamJSONTranslator {
    public static String teamToJson(Team team) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"teamName\":").append("\"").append(team.getTeamName()).append("\",");
        sb.append("\"memberUsernames\":").append(setToJsonArray(team.getMemberUsernames()));
        sb.append("}");
        return sb.toString();
    }

    public static Team jsonToTeam(String json) {
        String teamName = extractJsonValue(json, "teamName");
        String memberUsernamesJson = extractJsonArray(json, "memberUsernames");
        Set<String> memberUsernames = jsonArrayToSet(memberUsernamesJson);
        return new Team(teamName, memberUsernames);
    }

    private static String extractJsonValue(String json, String key) {
        String pattern = "\"" + key + "\":\"";
        int startIndex = json.indexOf(pattern) + pattern.length();
        int endIndex = json.indexOf("\"", startIndex);
        return json.substring(startIndex, endIndex);
    }

    private static String extractJsonArray(String json, String key) {
        String pattern = "\"" + key + "\":[";
        int startIndex = json.indexOf(pattern) + pattern.length();
        int endIndex = json.indexOf("]", startIndex);
        return json.substring(startIndex, endIndex);
    }

    private static String setToJsonArray(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int count = 0;
        for (String item : set) {
            sb.append("\"").append(item).append("\"");
            count++;
            if (count < set.size()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static Set<String> jsonArrayToSet(String jsonArray) {
        Set<String> set = new HashSet<>();
        if (jsonArray.trim().isEmpty()) {
            return set;
        }
        String[] items = jsonArray.split(",");
        for (String item : items) {
            String value = item.trim().replaceAll("^\"|\"$", "");
            set.add(value);
        }
        return set;
    }
}
