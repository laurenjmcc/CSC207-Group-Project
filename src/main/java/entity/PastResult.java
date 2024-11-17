package entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import data_access.DiseaseDataAccessObject;
public class PastResult {
        private static final Map<String, ArrayList<String>>  resultsMap = new HashMap<>();
        private DiseaseDataAccessObject disease;

    public void addResult(String proteinID) {
            this.disease = new DiseaseDataAccessObject(proteinID);
            resultsMap.put("Disease", disease.getDisease());
        }

        public ArrayList<String> getResult() {
            return resultsMap.get("Disease");
        }

    }

