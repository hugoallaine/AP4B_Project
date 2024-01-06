package src.jeu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CSVFileReader {
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
        }catch (IOException e){
            e.printStackTrace();
        }
        return Card_Data;
        
    }
    
}
