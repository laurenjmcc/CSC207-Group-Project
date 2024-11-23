package API;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;


public class APIRequest {

    public static void main(String[] args) throws Exception {
        String requestURL = "https://www.ebi.ac.uk/proteins/api/proteins?offset=0&size=1&protein=PIK3CA";
        URL url = new URL(requestURL);

        URLConnection connection = url.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection)connection;

        httpConnection.setRequestProperty("Accept", "application/json");


        InputStream response = connection.getInputStream();
        int responseCode = httpConnection.getResponseCode();

        if(responseCode != 200) {
            throw new RuntimeException("Response code was not 200. Detected response was "+responseCode);
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
        }
        finally {
            if (reader != null) try {
                reader.close();
            } catch (IOException logOrIgnore) {
                logOrIgnore.printStackTrace();
            }
        }
        JSONArray jsonArray = new JSONArray(output);
        String description = getDescription(jsonArray);
        System.out.println(description);
    }

    public static String getDescription(JSONArray input) {

        JSONObject jo1 = input.getJSONObject(0);
        JSONArray commentsArray = jo1.getJSONArray("comments");
        JSONObject jo2 = commentsArray.getJSONObject(0);
        JSONArray textArray = jo2.getJSONArray("text");
        JSONObject jo3 = textArray.getJSONObject(0);
        String outputString = jo3.getString("value");

        return outputString;
    };
}
