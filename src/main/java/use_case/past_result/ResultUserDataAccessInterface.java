package use_case.past_result;

import java.util.ArrayList;
import entity.PastResult;

/**
 * The interface of the DAO for the Change Password Use Case.
 */
public interface ResultUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     *
     * @param protein the user whose password is to be updated
     * @return
     */
    String get_description();
    String get_name();
    String get_id();
}
