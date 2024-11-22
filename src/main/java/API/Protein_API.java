package API;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Protein_API {
    private String protein_name;
    final private String protein_id;

    public Protein_API(String protein_name) {
        this.protein_name = protein_name;
        this.protein_id = getUniProtAccession(protein_name);

    }

    public static void main(String[] args) throws Exception {
        Protein_API protein_api = new Protein_API("p53");
        System.out.println(protein_api.location());
        System.out.println(protein_api.extractDisease());
        System.out.println(protein_api.acronyms());

    }

    public String getUniProtAccession(String proteinName) {
        if (proteinName == null || proteinName.trim().isEmpty()) {
            return "Protein name cannot be null or empty.";
        }
        try {
            String url = "https://rest.uniprot.org/uniprotkb/search?query=gene:" + proteinName + "&format=json";
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                InputStream errorStream = conn.getErrorStream();
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = errorReader.readLine()) != null) {
                    errorResponse.append(line);
                }
                errorReader.close();
                return "Server returned error: " + responseCode + " - " + errorResponse.toString();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray results = jsonResponse.getJSONArray("results");

            if (results.length() > 0) {
                JSONObject firstResult = results.getJSONObject(0);
                return firstResult.getString("primaryAccession");
            } else {
                return "No UniProt accession found for the given protein name.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred: " + e.getMessage();
        }
    }

    public JSONArray PI_API() throws Exception {
        String requestURL = String.format("https://www.ebi.ac.uk/proteins/api/proteins/interaction/%s", this.protein_id);
        URL url = new URL(requestURL);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection) connection;
        httpConnection.setRequestProperty("Accept", "application/json");
        InputStream response = connection.getInputStream();
        int responseCode = httpConnection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Response code was not 200. Detected response was " + responseCode);
        }
        String output;
        Reader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(response, "UTF-8"));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            output = builder.toString();
        } finally {
            if (reader != null) try {
                reader.close();
            } catch (IOException logOrIgnore) {
                logOrIgnore.printStackTrace();
            }
        }
        JSONArray jsonArray = new JSONArray(output);
        return jsonArray;
    }

    public ArrayList<String> acronyms() throws Exception {
        JSONArray something = PI_API();
        JSONObject jsonObject = something.getJSONObject(0);
        JSONArray diseases = jsonObject.getJSONArray("diseases");
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < diseases.length(); i++) {
            JSONObject disease = diseases.getJSONObject(i);
            if (disease.has("acronym")) {
                String acronym = disease.getString("acronym");
                output.add(acronym);
            }
        }
        return output;
    }

    public ArrayList<String> extractDisease() throws Exception {
        JSONArray something = PI_API();
        JSONArray jsonArray = new JSONArray(something);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        JSONArray diseases = jsonObject.getJSONArray("diseases");
        ArrayList<String> diseaseIds = new ArrayList<>();
        for (int i = 0; i < diseases.length(); i++) {
            JSONObject disease = diseases.getJSONObject(i);
            if (disease.has("diseaseId")) {
                String diseaseId = disease.getString("diseaseId");
                diseaseIds.add(diseaseId);
            }
        }
        return diseaseIds;
    }

    public String getDescription() throws Exception {
        String requestURL = String.format("https://www.ebi.ac.uk/proteins/api/proteins/interaction/%s", this.protein_id);
        URL url = new URL(requestURL);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection) connection;
        httpConnection.setRequestProperty("Accept", "application/json");
        InputStream response = connection.getInputStream();
        int responseCode = httpConnection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Response code was not 200. Detected response was " + responseCode);
        }
        String output;
        Reader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(response, "UTF-8"));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            output = builder.toString();
        } finally {
            if (reader != null) try {
                reader.close();
            } catch (IOException logOrIgnore) {
                logOrIgnore.printStackTrace();
            }
        }
        return output;
    }

    public Map<String, Set<String>> location() throws Exception {
        Map<String, Set<String>> diseaseToLocationsMap = new HashMap<>();
        JSONArray jsonArray = PI_API();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            processObject(obj, "subcellularLocations", diseaseToLocationsMap);
        }

        return diseaseToLocationsMap;
    }

    private void processObject(JSONObject obj, String key, Map<String, Set<String>> resultMap) throws Exception {
        if (obj.has("diseases")) {
            JSONArray diseases = obj.getJSONArray("diseases");

            for (int j = 0; j < diseases.length(); j++) {
                JSONObject disease = diseases.getJSONObject(j);
                String diseaseId = disease.optString("diseaseId", "Unknown Disease");
                if (obj.has(key)) {
                    JSONArray targetArray = obj.getJSONArray(key);
                    Set<String> locations = extractLocations(targetArray);
                    resultMap.merge(diseaseId, locations, (existing, newLocations) -> {
                        existing.addAll(newLocations);
                        return existing;
                    });
                }
            }
        }

        for (String nestedKey : obj.keySet()) {
            Object value = obj.get(nestedKey);
            if (value instanceof JSONObject) {
                processObject((JSONObject) value, key, resultMap);
            } else if (value instanceof JSONArray) {
                JSONArray array = (JSONArray) value;
                for (int i = 0; i < array.length(); i++) {
                    if (array.get(i) instanceof JSONObject) {
                        processObject(array.getJSONObject(i), key, resultMap);
                    }
                }
            }
        }
    }

    private Set<String> extractLocations(JSONArray subcellularLocations) {
        Set<String> locations = new HashSet<>();
        for (int k = 0; k < subcellularLocations.length(); k++) {
            JSONObject locationObj = subcellularLocations.getJSONObject(k);
            JSONArray locationsArray = locationObj.optJSONArray("locations");
            if (locationsArray != null) {
                for (int l = 0; l < locationsArray.length(); l++) {
                    JSONObject location = locationsArray.getJSONObject(l).optJSONObject("location");
                    if (location != null) {
                        String locationValue = location.optString("value", "Unknown Location");
                        String[] splitValues = locationValue.split(",");
                        for (String splitValue : splitValues) {
                            locations.add(splitValue.trim());
                        }
                    }
                }
            }
        }
        return locations;
    }
}



