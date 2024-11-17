package utility;

import entity.User;
import entity.CommonUser;
import java.util.Set;
import java.util.HashSet;

public class UserJSONTranslator {

    public static String userToJson(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"name\":").append("\"").append(user.getName()).append("\",");
        sb.append("\"password\":").append("\"").append(user.getPassword()).append("\",");
        sb.append("\"teamNames\":").append(setToJsonArray(user.getTeamNames()));
        sb.append("}");
        return sb.toString();
    }

    public static User jsonToUser(String json) {
        String name = extractJsonValue(json, "name");
        String password = extractJsonValue(json, "password");
        String teamNamesJson = extractJsonArray(json, "teamNames");
        Set<String> teamNames = jsonArrayToSet(teamNamesJson);
        User user = new CommonUser(name, password);
        for (String teamName : teamNames) {
            user.addTeamName(teamName);
        }
        return user;
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
