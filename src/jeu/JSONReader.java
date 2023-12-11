package src.jeu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class JSONReader {
    //TODO
    // public static Hashtable<String, String> read(String filePath) throws IllegalArgumentException{

    //     if(filePath == null) throw new IllegalArgumentException("[ERROR] The path given is null!");
    //     if(!filePath.split("\\.")[1].equalsIgnoreCase("json")) throw new IllegalArgumentException("[ERROR] The file must be a JSON file!");

    //     ArrayList<Hashtable<String, String>> list = new ArrayList<Hashtable<String, String>>();

    //     StringBuilder fileContent = new StringBuilder();
    //     try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
    //         String line;
    //         while ((line = br.readLine()) != null) {
    //             if(!line.matches("[\\{\\}\\[\\]]")){
    //                 fileContent.append(line.stripIndent().strip());
    //             }
    //         }
            
    //     }
    //     catch (IOException e) {
    //         System.err.println("[ERROR] Cannot read file " + filePath);
    //         e.printStackTrace();
    //     }
    //     System.out.println(fileContent);
    //     for(String object : fileContent.toString().split(",")){
            
    //     }

    //     System.out.println(list);
    //     return list;
    // }

    // TODO
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
