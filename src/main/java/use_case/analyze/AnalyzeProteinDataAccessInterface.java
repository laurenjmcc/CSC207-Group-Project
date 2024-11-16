package use_case.analyze;

public interface AnalyzeProteinDataAccessInterface {

    String getProteinDescription(String proteinname);

    boolean successCall(String proteinname);
}
