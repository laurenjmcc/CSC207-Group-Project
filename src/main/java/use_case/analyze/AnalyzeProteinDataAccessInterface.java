package use_case.analyze;

import java.util.ArrayList;

public interface AnalyzeProteinDataAccessInterface {

    boolean successCall(String proteinname);

    ArrayList<String> DiseaseInfo() throws Exception;

    String getProteinDescription() throws Exception;
}
