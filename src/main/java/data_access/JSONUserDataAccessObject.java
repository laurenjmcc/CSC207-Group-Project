package data_access;

import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class JSONUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {

    private final String filePath;
    private final UserFactory userFactory;
    private final Map<String, User> users = new HashMap<>();
    private String currentUsername;

    public JSONUserDataAccessObject(String filePath, UserFactory userFactory) {
        this.filePath = filePath;
        this.userFactory = userFactory;
        loadUsersFromFile();
    }

    private void loadUsersFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            saveUsersToFile();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder jsonBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    jsonBuilder.append(line);
                }

                String jsonData = jsonBuilder.toString();
                if (!jsonData.isEmpty()) {
                    JSONArray usersArray = new JSONArray(jsonData);
                    for (int i = 0; i < usersArray.length(); i++) {
                        JSONObject userObject = usersArray.getJSONObject(i);
                        String username = userObject.getString("username");
                        String password = userObject.getString("password");
                        User user = userFactory.create(username, password);

                        JSONArray teamNamesArray = userObject.optJSONArray("teamNames");
                        if (teamNamesArray != null) {
                            for (int j = 0; j < teamNamesArray.length(); j++) {
                                user.addTeamName(teamNamesArray.getString(j));
                            }
                        }

                        users.put(username, user);
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException("Error reading user data from file", e);
            }
        }
    }

    private void saveUsersToFile() {
        JSONArray usersArray = new JSONArray();
        for (User user : users.values()) {
            JSONObject userObject = new JSONObject();
            userObject.put("username", user.getName());
            userObject.put("password", user.getPassword());
            JSONArray teamNamesArray = new JSONArray(user.getTeamNames());
            userObject.put("teamNames", teamNamesArray);

            usersArray.put(userObject);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(usersArray.toString(2));
        } catch (IOException e) {
            throw new RuntimeException("Error writing user data to file", e);
        }
    }

    @Override
    public boolean existsByName(String username) {
        return users.containsKey(username);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
        saveUsersToFile();
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(User user) {
        users.put(user.getName(), user);
        saveUsersToFile();
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }
}
