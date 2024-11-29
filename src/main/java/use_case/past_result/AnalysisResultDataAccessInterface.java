package use_case.past_result;

import entity.AnalysisResult;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface AnalysisResultDataAccessInterface {
    void saveResult(AnalysisResult result) throws IOException;

    List<AnalysisResult> getResultsForUsers(Set<String> usernames) throws IOException;
}
