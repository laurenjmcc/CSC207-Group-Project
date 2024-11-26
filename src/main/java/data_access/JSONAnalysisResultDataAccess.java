package data_access;

import entity.AnalysisResult;
import use_case.past_result.AnalysisResultDataAccessInterface;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

public class JSONAnalysisResultDataAccess implements AnalysisResultDataAccessInterface {
    private final String filePath;

    public JSONAnalysisResultDataAccess(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public synchronized void saveResult(AnalysisResult result) throws IOException {
        JSONArray data = readJsonFile();

        JSONObject resultJson = new JSONObject();
        resultJson.put("username", result.getUsername());
        resultJson.put("teamName", result.getTeamName());
        resultJson.put("proteinName", result.getProteinName());
        resultJson.put("analysisDate", result.getAnalysisDate().toString());
        resultJson.put("analysisData", new JSONObject(result.getAnalysisData()));

        data.put(resultJson);
        writeJsonFile(data);
    }

    @Override
    public synchronized List<AnalysisResult> getResultsForTeam(String teamName) throws IOException {
        JSONArray data = readJsonFile();
        List<AnalysisResult> results = new ArrayList<>();

        for (int i = 0; i < data.length(); i++) {
            JSONObject obj = data.getJSONObject(i);
            if (obj.getString("teamName").equals(teamName)) {
                AnalysisResult result = new AnalysisResult();
                result.setUsername(obj.getString("username"));
                result.setTeamName(obj.getString("teamName"));
                result.setProteinName(obj.getString("proteinName"));
                result.setAnalysisDate(LocalDateTime.parse(obj.getString("analysisDate")));
                result.setAnalysisData(obj.getJSONObject("analysisData").toMap());
                results.add(result);
            }
        }

        return results;
    }

    private JSONArray readJsonFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new JSONArray();
        }
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            String jsonData = jsonBuilder.toString().trim();
            return jsonData.isEmpty() ? new JSONArray() : new JSONArray(jsonData);
        }
    }

    private void writeJsonFile(JSONArray data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(filePath, StandardCharsets.UTF_8))) {
            writer.write(data.toString(2)); // Indent factor of 2
        }
    }
}
