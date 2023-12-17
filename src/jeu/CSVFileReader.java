package src.jeu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CSVFileReader {
    public static Hashtable<String, ArrayList<String>> readCSVTest (String filePath) throws IllegalArgumentException {

        if(filePath == null) throw new IllegalArgumentException("[ERROR] The path given is null!");
        if(!filePath.split("\\.")[1].equals("csv")) throw new IllegalArgumentException("[ERROR] The file must be a CSV file!");

        Hashtable<String, ArrayList<String>> cardDataList = new Hashtable<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String[] keys = br.readLine().split(";");
            
            for(int i = 0; i < keys.length; i++){
                cardDataList.put(keys[i], new ArrayList<String>());
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                for(int i = 0; i < keys.length; i++){
                    cardDataList.get(keys[i]).add(data[i]);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return cardDataList;
    }


    public static List<String[]> readCSV(String filePath){
        if(filePath == null) throw new IllegalArgumentException("[ERROR] The path given is null!");
        if(!filePath.split("\\.")[1].equals("csv")) throw new IllegalArgumentException("[ERROR] The file must be a CSV file!");
        List<String[]> Card_Data=new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String ligne;
            while ((ligne=br.readLine())!= null) {
                String[] ligne_split=ligne.split(";");
                Card_Data.add(ligne_split);


                
            }
            
        }catch (IOException e){
            e.printStackTrace();
        }
        return Card_Data;
        
    }
    
}
