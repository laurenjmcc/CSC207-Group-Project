package data_access;

import use_case.analyze.AnalyzeProteinDataAccessInterface;

public class ProteinDataAccessObject implements AnalyzeProteinDataAccessInterface {

    @Override
    public String getProteinDescription(String proteinname) {
        return "";
    }

    /**
     * @param proteinname
     * @return
     */
    @Override
    public boolean successCall(String proteinname) {
        return true;
    }
}