package use_case.analyze;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface AnalyzeProteinDataAccessInterface {

    String getProteinname();

    ArrayList<String> getDisease();

    ArrayList<String> getAcronym();

    Map<String, Set<String>> getlocation();

    String getProteinDescription();

    boolean successCall(String proteinname);
}
