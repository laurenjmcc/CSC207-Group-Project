package entity;

import data_access.ProteinDataAccessObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PastResult {
    private static Map<String, Set<String>> resultsMap;
    private ProteinDataAccessObject disease;
    private ArrayList<String> extract_disease;
    private Map<String, Set<String>> main_result = new HashMap<>();

    public static void main(String[] args) throws Exception {
        PastResult pastResult = new PastResult("p53");
        System.out.println(pastResult.getMainResult());

    }
    public PastResult(String proteinID) throws Exception {
        this.disease = new ProteinDataAccessObject(proteinID);
        resultsMap = disease.getResult();
        this.extract_disease = disease.getDisease();
    }

    public Map<String, Set<String>> getMainResult() {
        for (String s : extract_disease) {
            main_result.put(s, resultsMap.get(s));
        }
        return main_result;
    }
}

