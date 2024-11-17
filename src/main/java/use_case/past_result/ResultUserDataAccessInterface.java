package use_case.past_result;

import java.util.ArrayList;

/**
 * The interface of the DAO for the Past Result Use Case.
 */
public interface ResultUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     *
     * @param protein the user whose password is to be updated
     * @return
     */
    ArrayList<String> DiseaseInfo(String protein) throws Exception;
}
