package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final Set<String> teamNames;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.teamNames = new HashSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Set<String> getTeamNames() {
        return teamNames;
    }

    @Override
    public void addTeamName(String teamName) {
        teamNames.add(teamName);

    }

}
