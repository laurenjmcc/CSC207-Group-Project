package use_case.team;

import entity.Team;

public interface TeamDataAccessInterface {

    /**
     * Checks if the given team exists.
     * @param teamName the username to look for
     * @return true if a team with the given username exists; false otherwise
     */
    boolean teamExists(String teamName);

    /**
     * Saves the user.
     * @param team the user to save
     */
    void saveTeam(Team team);

    Team getTeam(String teamName);
}
