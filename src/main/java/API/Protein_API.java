package API;

import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Protein_API {
    public static void main(String[] args) throws Exception {
        System.out.println(acronyms("P04637"));
        System.out.println(extractDiseaseIds("P04637"));
        JSONArray something = PI_API("P04637");
        JSONObject jsonObject = something.getJSONObject(0);
        JSONArray diseases = jsonObject.getJSONArray("diseases");
        System.out.println(diseases);


    }

    public static JSONArray PI_API(String proteinID) throws Exception {
        String requestURL = String.format("https://www.ebi.ac.uk/proteins/api/proteins/interaction/%s", proteinID);
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

    public static ArrayList<String> acronyms(String proteinID) throws Exception {
        JSONArray something = PI_API(proteinID);
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
    public static ArrayList<String> extractDiseaseIds(String jsonString) throws Exception {
        JSONArray something = PI_API(jsonString);
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

}

