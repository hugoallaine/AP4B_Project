package src.jeu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Classe permettant de lire un fichier CSV
 */
public class CSVFileReader {

    /**
     * @brief Méthode permettant de lire un fichier CSV
     * @param filePath Chemin du fichier CSV
     * @return Liste de String[] contenant les données du fichier CSV
     */
    public static List<String[]> readCSV(String filePath){
        if(filePath == null) throw new IllegalArgumentException("[ERROR] The path given is null!");
        if(!filePath.split("\\.")[1].equals("csv")) throw new IllegalArgumentException("[ERROR] The file must be a CSV file!");
        List<String[]> Card_Data=new ArrayList<>();
        try (final BufferedReader br = new BufferedReader(new FileReader(filePath))){
            br.readLine();
            String ligne;
            while ((ligne=br.readLine())!= null) {
                String[] ligne_split=ligne.split(";");
                for(final String data : ligne_split) {
                    data.trim();
                }
                Card_Data.add(ligne_split);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Card_Data;   
    }   
}