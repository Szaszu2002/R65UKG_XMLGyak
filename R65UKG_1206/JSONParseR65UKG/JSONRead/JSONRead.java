package R65UKG;

import java.util.Map;
import json.simple;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.json.simple.*;
import org.json.simple.parser.*;

public class JSONRead {

	public static void main(String[] args) {
        JsonParser parser = new JSONParser();
        JSONObject all = (JSONObject)parser.parse(new FileReader("R65UKG_1206/JSONParseR65UKG/JSONRead/kurzusfelvetelR65UKG.json"));
        
        printFile(all);
    }

    public static void printFile(Object all){
        if(all instanceof JSONObject){
            JSONObject object = (JSONObject)toPrint;
            System.out.println("{ ");
            String[] keys = Arrays.copyOf(object.keySet().toArray(), object.keySet().toArray().length, String[].class);
            for(int i; i<keys.length; i++){
                System.out.print("\"" + keys[i] + "\": ");
                printFile(object.get[keys[i]]);
                if(i != keys.length-1){
                    System.out.println(", ");
                }
                else{
                    System.out.println();
                }
            }
            System.out.print(" }");
        }
        if(all instanceof JSONArray){
            JSONArray array = (JSONArray)toPrint;
            System.out.println("[ ");
            for (int i = 0; i < array.size(); i++){
				printFile(array.get(i));
				if (i != array.size() - 1){
					System.out.println(", ");
				}
				else{
					System.out.println();
				}
			}
			System.out.println(" ]");
        }
        if (all instanceof String) {
            System.out.print("\"" + all + "\"");
        }
		if (all instanceof Long) {
            System.out.print(all);
        }
    }
}