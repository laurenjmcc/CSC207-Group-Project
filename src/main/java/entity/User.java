package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    Set<String> getTeamNames();

    void addTeamName(String teamName);
}
