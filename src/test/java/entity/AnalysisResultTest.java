package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test cases for the AnalysisResult class.
 */
class AnalysisResultTest {
    private AnalysisResult analysisResult;
    private String username;
    private List<String> teamNames;
    private String proteinName;
    private LocalDateTime analysisDate;
    private Map<String, Object> analysisData;

    @BeforeEach
    void setUp() {
        username = "user";
        teamNames = new ArrayList<>(Arrays.asList("Team1", "2"));
        proteinName = "Hemoglobin";
        analysisDate = LocalDateTime.of(2023, 10, 15, 10, 30);
        analysisData = new HashMap<>();
        analysisData.put("score", 95);
        analysisData.put("details", "analysis");

        analysisResult = new AnalysisResult();
        analysisResult.setUsername(username);
        analysisResult.setTeamNames(teamNames);
        analysisResult.setProteinName(proteinName);
        analysisResult.setAnalysisDate(analysisDate);
        analysisResult.setAnalysisData(analysisData);
    }


    @Test
    void testGetUsernameShouldReturnCorrectUsername() {
        assertEquals(username, analysisResult.getUsername());
    }

    @Test
    void testSetUsernameShouldUpdateUsernameCorrectly() {
        String newUsername = "username";
        analysisResult.setUsername(newUsername);
        assertEquals(newUsername, analysisResult.getUsername());
    }

    @Test
    void testSetUsernameWithNullShouldAllowNull() {
        analysisResult.setUsername(null);
        assertNull(analysisResult.getUsername());
    }

    @Test
    void testSetUsername_WithEmptyString_ShouldAllowEmptyString() {
        String emptyUsername = "   ";
        analysisResult.setUsername(emptyUsername);
        assertEquals(emptyUsername, analysisResult.getUsername());
    }

    @Test
    void testSetGetTeamNames_ShouldReturnCorrectTeamNames() {
        assertEquals(teamNames, analysisResult.getTeamNames());
    }

    @Test
    void testSetTeamNames_ShouldUpdateTeamNamesCorrectly() {
        List<String> newTeamNames = new ArrayList<>(Arrays.asList("Team3", "Team4"));
        analysisResult.setTeamNames(newTeamNames);
        assertEquals(newTeamNames, analysisResult.getTeamNames());
    }

    @Test
    void testSetTeamNames_WithNull_ShouldAllowNull() {
        analysisResult.setTeamNames(null);
        assertNull(analysisResult.getTeamNames());
    }

    @Test
    void testSetTeamNames_WithEmptyList_ShouldAllowEmptyList() {
        List<String> emptyTeamNames = new ArrayList<>();
        analysisResult.setTeamNames(emptyTeamNames);
        assertEquals(emptyTeamNames, analysisResult.getTeamNames());
    }

    @Test
    void getTeamNames_ShouldReturnReferenceToInternalList() {
        List<String> retrievedTeamNames = analysisResult.getTeamNames();
        retrievedTeamNames.add("Team5");
        assertTrue(analysisResult.getTeamNames().contains("Team5"));
    }

    @Test
    void testGetProteinName_ShouldReturnCorrectProteinName() {
        assertEquals(proteinName, analysisResult.getProteinName());
    }

    @Test
    void testSetProteinName_ShouldUpdateProteinNameCorrectly() {
        String newProteinName = "p53";
        analysisResult.setProteinName(newProteinName);
        assertEquals(newProteinName, analysisResult.getProteinName());
    }

    @Test
    void testSetProteinName_WithNull_ShouldAllowNull() {
        analysisResult.setProteinName(null);
        assertNull(analysisResult.getProteinName());
    }

    @Test
    void testSetProteinName_WithEmptyString_ShouldAllowEmptyString() {
        String emptyProteinName = "   ";
        analysisResult.setProteinName(emptyProteinName);
        assertEquals(emptyProteinName, analysisResult.getProteinName());
    }

    @Test
    void testGetAnalysisDate_ShouldReturnCorrectAnalysisDate() {
        assertEquals(analysisDate, analysisResult.getAnalysisDate());
    }

    @Test
    void testSetAnalysisDate_ShouldUpdateAnalysisDateCorrectly() {
        LocalDateTime newAnalysisDate = LocalDateTime.of(2024, 1, 1, 12, 0);
        analysisResult.setAnalysisDate(newAnalysisDate);
        assertEquals(newAnalysisDate, analysisResult.getAnalysisDate());
    }

    @Test
    void testSetAnalysisDate_WithNull_ShouldAllowNull() {
        analysisResult.setAnalysisDate(null);
        assertNull(analysisResult.getAnalysisDate());
    }


    @Test
    void testGetAnalysisData_ShouldReturnCorrectAnalysisData() {
        assertEquals(analysisData, analysisResult.getAnalysisData());
    }

    @Test
    void testSetAnalysisData_ShouldUpdateAnalysisDataCorrectly() {
        Map<String, Object> newAnalysisData = new HashMap<>();
        newAnalysisData.put("score", 88);
        newAnalysisData.put("details", "analysis");
        analysisResult.setAnalysisData(newAnalysisData);
        assertEquals(newAnalysisData, analysisResult.getAnalysisData());
    }

    @Test
    void testSetAnalysisData_WithNull_ShouldAllowNull() {
        analysisResult.setAnalysisData(null);
        assertNull(analysisResult.getAnalysisData());
    }

    @Test
    void testGetAnalysisData_ShouldReturnReferenceToInternalMap() {
        Map<String, Object> retrievedAnalysisData = analysisResult.getAnalysisData();
        retrievedAnalysisData.put("newKey", "newValue");
        assertTrue(analysisResult.getAnalysisData().containsKey("newKey"));
    }
}
