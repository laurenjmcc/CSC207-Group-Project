package API;

import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.StructureIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ProteinStructureFetcher {
    // Existing getStructure method...
    public static Structure getStructure(String pdbCode) throws Exception {
        Structure structure = StructureIO.getStructure(pdbCode);
        return structure;
    }

    public static String getPdbCodeFromProteinName(String proteinName) throws Exception {

        // Encode the protein name for URL
        String query = URLEncoder.encode(proteinName, "UTF-8");

        // Updated UniProt REST API URL
        String urlStr = String.format("https://rest.uniprot.org/uniprotkb/search?query=" + query + "&format=tsv&fields=accession,xref_pdb");

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0"); // Set User-Agent

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("Server returned HTTP response code: " + responseCode + " for URL: " + urlStr);
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine).append("\n");
        }
        in.close();

        // Parse the response to extract the PDB code
        String[] lines = response.toString().split("\n");
        if (lines.length > 1) {
            // Skip the header line
            for (int i = 1; i < lines.length; i++) {
                String[] columns = lines[i].split("\t");
                if (columns.length > 1 && !columns[1].isEmpty()) {
                    // columns[1] contains the xref_pdb field
                    // Return the first PDB code
                    String pdbCodes = columns[1];
                    return pdbCodes.split(";")[0].trim();
                }
            }
        }

        throw new Exception("No PDB code found for protein name: " + proteinName);
    }
}


