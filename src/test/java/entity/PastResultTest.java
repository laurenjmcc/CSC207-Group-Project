package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PastResultTest {

    private PastResult pastResult;
    private final String description = "Ras proteins bind GDP/GTP and possess intrinsic GTPase activity ." +
            " Plays an important role in the regulation of cell proliferation ." +
            " Plays a role in promoting oncogenic events by inducing transcriptional silencing of tumor suppressor genes in colorectal cancer cells in a ZNF304-dependent manner";
    private final String id = "P01116";
    private final String proteinName = "KRAS";

    @BeforeEach
    void setUp() throws Exception {
        String proteinID = "KRAS";
        pastResult = new PastResult(proteinID);
        Field resultsField = PastResult.class.getDeclaredField("results");
        resultsField.setAccessible(true);
        List<PastResult.PastResultEntry> results = (List<PastResult.PastResultEntry>) resultsField.get(null);
        results.clear();
    }

    @Test
    void testConstructorShouldInitializeAllFieldsCorrectly() {
        try {
            Field descriptionField = PastResult.class.getDeclaredField("description");
            descriptionField.setAccessible(true);
            String actualDescription = (String) descriptionField.get(pastResult);
            assertEquals(description, actualDescription);

            Field idField = PastResult.class.getDeclaredField("id");
            idField.setAccessible(true);
            String actualId = (String) idField.get(pastResult);
            assertEquals(id, actualId);

            Field proteinNameField = PastResult.class.getDeclaredField("proteinName");
            proteinNameField.setAccessible(true);
            String actualProteinName = (String) proteinNameField.get(pastResult);
            assertEquals(proteinName, actualProteinName);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Reflection failed: " + e.getMessage());
        }

        String expectedFormattedDescription = description.replaceAll("\\.", ".\n");
        assertEquals(expectedFormattedDescription, pastResult.getDescription());
    }

    @Test
    void testAddShouldAddEntryToResults() {
        pastResult.add();
        List<PastResult.PastResultEntry> results = PastResult.getResults();
        assertEquals(1, results.size());
        PastResult.PastResultEntry entry = results.get(0);
        assertEquals(proteinName, entry.getProteinName());
        assertEquals(description.replaceAll("\\.", ".\n"), entry.getDescription());
        assertEquals(id, entry.getId());
    }

    @Test
    void testAddMultipleEntriesShouldAddAllEntries() {
        pastResult.add();
        pastResult.add();
        List<PastResult.PastResultEntry> results = PastResult.getResults();
        assertEquals(2, results.size());
        for (PastResult.PastResultEntry entry : results) {
            assertEquals(proteinName, entry.getProteinName());
            assertEquals(description.replaceAll("\\.", ".\n"), entry.getDescription());
            assertEquals(id, entry.getId());
        }
    }

    @Test
    void testGetDescriptionShouldFormatDescriptionWithNewlines() {
        String expectedDescription = description.replaceAll("\\.", ".\n");
        assertEquals(expectedDescription, pastResult.getDescription());
    }

    @Test
    void testGetDescriptionWithNoPeriodsShouldReturnOriginalDescription() throws Exception {
        try {
            Field descriptionField = PastResult.class.getDeclaredField("description");
            descriptionField.setAccessible(true);
            descriptionField.set(pastResult, "This is a description without periods");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Reflection failed: " + e.getMessage());
        }

        String expectedDescription = "This is a description without periods";
        assertEquals(expectedDescription, pastResult.getDescription());
    }

    @Test
    void testGetResultsShouldReturnCopyOfResultsList() {
        pastResult.add();
        List<PastResult.PastResultEntry> resultsCopy = PastResult.getResults();

        assertEquals(1, resultsCopy.size(), "Results copy should have one entry.");
        resultsCopy.add(new PastResult.PastResultEntry("FakeProtein", "FakeDescription.", "FAKE123"));

        List<PastResult.PastResultEntry> actualResults = PastResult.getResults();
        assertEquals(1, actualResults.size(), "Actual results should not be affected by modifications to the copy.");
    }

    @Test
    void testGetResultsWhenNoEntriesShouldReturnEmptyList() {
        List<PastResult.PastResultEntry> results = PastResult.getResults();
        assertTrue(results.isEmpty());
    }

    @Test
    void testPastResultEntryShouldInitializeFieldsCorrectly() {
        PastResult.PastResultEntry entry = new PastResult.PastResultEntry("ProteinX", "DescriptionX.", "ID123");

        assertEquals("ProteinX", entry.getProteinName(), "Protein name should match the input.");
        assertEquals("DescriptionX.\n", entry.getDescription(),
                "Description should be formatted with a newline after the period.");
        assertEquals("ID123", entry.getId(), "ID should match the input.");
    }

    @Test
    void testPastResultEntrytoStringShouldReturnCorrectFormat() {
        PastResult.PastResultEntry entry = new PastResult.PastResultEntry("ProteinY", "DescriptionY.", "ID456");
        String expectedString = "Protein: ProteinY(ID456) Description:" + "DescriptionY.\n";
        assertEquals(expectedString, entry.toString());
    }

    @Test
    void testStaticResultsListShouldBeSharedAcrossInstances() throws Exception {
        String anotherProteinID = "p53";
        PastResult anotherPastResult = new PastResult(anotherProteinID);

        try {
            Field descriptionField = PastResult.class.getDeclaredField("description");
            descriptionField.setAccessible(true);
            descriptionField.set(anotherPastResult, "Ras proteins bind GDP/GTP and possess intrinsic GTPase activity ." +
                    " Plays an important role in the regulation of cell proliferation ." +
                    " Plays a role in promoting oncogenic events by inducing transcriptional silencing of tumor suppressor genes in colorectal cancer cells in a ZNF304-dependent manner");

            Field idField = PastResult.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(anotherPastResult, "P01116");

            Field proteinNameField = PastResult.class.getDeclaredField("proteinName");
            proteinNameField.setAccessible(true);
            proteinNameField.set(anotherPastResult, "KRAS");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Reflection failed: " + e.getMessage());
        }
        anotherPastResult.add();
        pastResult.add();
        List<PastResult.PastResultEntry> results = PastResult.getResults();
        assertEquals(2, results.size());
        PastResult.PastResultEntry firstEntry = results.get(0);
        PastResult.PastResultEntry secondEntry = results.get(1);
        assertEquals(proteinName, firstEntry.getProteinName());
        assertEquals(description.replaceAll("\\.", ".\n"), firstEntry.getDescription());
        assertEquals(id, firstEntry.getId(), "First entry's ID should match.");
        assertEquals("KRAS", secondEntry.getProteinName());
        assertEquals("P01116", secondEntry.getId());
    }
}
