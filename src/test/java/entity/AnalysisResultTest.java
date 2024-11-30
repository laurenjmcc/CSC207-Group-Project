package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AnalysisResultTest {

    private AnalysisResult analysisResult;

    @BeforeEach
    void setUp() {
        // Initialize with default constructor before each test
        analysisResult = new AnalysisResult();
    }

    @Test
    void testDefaultConstructor() {
        assertNull(analysisResult.getUsername(), "Username should be null by default");
        assertNull(analysisResult.getTeamNames(), "TeamNames should be null by default");
        assertNull(analysisResult.getProteinName(), "ProteinName should be null by default");
        assertNull(analysisResult.getAnalysisDate(), "AnalysisDate should be null by default");
        assertNull(analysisResult.getAnalysisData(), "AnalysisData should be null by default");
    }

    @Test
    void testParameterizedConstructor() {
        String username = "john_doe";
        List<String> teamNames = Arrays.asList("TeamA", "TeamB");
        String proteinName = "Hemoglobin";
        LocalDateTime analysisDate = LocalDateTime.now();
        Map<String, Object> analysisData = new HashMap<>();
        analysisData.put("key1", "value1");
        analysisData.put("key2", 123);

        AnalysisResult result = new AnalysisResult(username, teamNames, proteinName, analysisDate, analysisData);

        assertEquals(username, result.getUsername(), "Username should match the provided value");
        assertEquals(teamNames, result.getTeamNames(), "TeamNames should match the provided list");
        assertEquals(proteinName, result.getProteinName(), "ProteinName should match the provided value");
        assertEquals(analysisDate, result.getAnalysisDate(), "AnalysisDate should match the provided value");
        assertEquals(analysisData, result.getAnalysisData(), "AnalysisData should match the provided map");
    }

    @Test
    void testGetAndSetUsername() {
        String username = "alice";
        analysisResult.setUsername(username);
        assertEquals(username, analysisResult.getUsername(), "getUsername should return the value set by setUsername");
    }

    @Test
    void testGetAndSetTeamNames() {
        List<String> teamNames = Arrays.asList("Alpha", "Beta", "Gamma");
        analysisResult.setTeamNames(teamNames);
        assertEquals(teamNames, analysisResult.getTeamNames(), "getTeamNames should return the value set by setTeamNames");

        // Test with an empty list
        analysisResult.setTeamNames(new ArrayList<>());
        assertTrue(analysisResult.getTeamNames().isEmpty(), "TeamNames should be empty after setting an empty list");

        // Test with null
        analysisResult.setTeamNames(null);
        assertNull(analysisResult.getTeamNames(), "TeamNames should be null after setting null");
    }

    @Test
    void testGetAndSetProteinName() {
        String proteinName = "Myoglobin";
        analysisResult.setProteinName(proteinName);
        assertEquals(proteinName, analysisResult.getProteinName(), "getProteinName should return the value set by setProteinName");
    }

    @Test
    void testGetAndSetAnalysisDate() {
        LocalDateTime analysisDate = LocalDateTime.of(2023, 10, 1, 12, 30);
        analysisResult.setAnalysisDate(analysisDate);
        assertEquals(analysisDate, analysisResult.getAnalysisDate(), "getAnalysisDate should return the value set by setAnalysisDate");

        // Test with null
        analysisResult.setAnalysisDate(null);
        assertNull(analysisResult.getAnalysisDate(), "AnalysisDate should be null after setting null");
    }

    @Test
    void testGetAndSetAnalysisData() {
        Map<String, Object> analysisData = new HashMap<>();
        analysisData.put("temperature", 37.0);
        analysisData.put("pH", 7.4);
        analysisResult.setAnalysisData(analysisData);
        assertEquals(analysisData, analysisResult.getAnalysisData(), "getAnalysisData should return the value set by setAnalysisData");

        // Test with an empty map
        analysisResult.setAnalysisData(new HashMap<>());
        assertTrue(analysisResult.getAnalysisData().isEmpty(), "AnalysisData should be empty after setting an empty map");

        // Test with null
        analysisResult.setAnalysisData(null);
        assertNull(analysisResult.getAnalysisData(), "AnalysisData should be null after setting null");
    }
}
