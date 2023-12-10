package R65UKG;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWrite {

	public static void main(String[] args) {
        JSONObject all = new JSONObject();
		JSONObject root = new JSONObject();

        JSONObject student = createStudent("Szabó Alexandra Marianna", "2002", "progrmatervező informatikus");
        root.put("hallgato", student);

        String[] names = {};
        int[] credits = {};
        String[] locations = {};
        String[] times = {};
        String[] lecturers = {};

        JSONObject courses = new JSONObject();

        if(names.length == 1){
            JSONObject object = createCourse(names[0], credits[0], locations[0], times[0], lecturers[0]);
            courses.put("kurzus", object);
        }
        else{
            JSONArray array = new JSONArray();
            for(int i =0; i<names.length; i++){
                array.add(createCourse(names[i], credits[i], locations[i], times[i], lecturers[i]));
            }
            courses.put("kurzus", array);
        }
        root.put("kurzusok", courses);
        all.put("R65UKG_Kurzusfelvetel", root);

        try{
            write = new FileWriter("R65UKG_1206/JSONParseR65UKG/JSONWrite/kurzusfelvetelR65UKG1.json", StandardCharsets.UTF_8);
            write(all);
        } catch(IOException e){
            System.err.println("Nem sikerült kiírni a fájlba!");
        }
    }

    public static JSONObject createStudent(String name, string year, String major){
        JSONObject student = new JSONObject();
        student.put("hnev", name);
		student.put("szulev", year);
		student.put("szak", major);
		return student;
    }

    public static JSONObject createCourse( Sting name, int credit, String location, String time, String lecturer){
        JSONObject course = new JSONObject();
		course.put("kurzusnev", name);
		course.put("kredit", credit);
		course.put("hely", location);
		course.put("idopont", time);
        course.put("oktato", lecturer);
        return course;
    }
    static FileWriter write;

    public static void write(Object all){
        if(all instanceof JSONObject){
            JSONObject object = (JSONObject)toPrint;
            write.write("{\n ");
            String[] keys = Arrays.copyOf(object.keySet().toArray(), object.keySet().toArray().length, String[].class);
            for(int i; i<keys.length; i++){
                write.write("\"" + keys[i] + "\": ");
                write(object.get[keys[i]]);
                if(i != keys.length-1){
                    write.write(",\n ");
                }
                else{
                    write.write("\n");
                }
            }
            write.write(" }");
        }
        if(all instanceof JSONArray){
            JSONArray array = (JSONArray)toPrint;
            write.write("[ ");
            for (int i = 0; i < array.size(); i++){
				write(array.get(i));
				if (i != array.size() - 1){
					write.write(",\n ");
				}
				else{
					write.write("\n");
				}
			}
			write.write(" ]");
        }
        if (all instanceof String) {
            write.write("\"" + all + "\"");
        }
		if (all instanceof Integer) {
            write.write(String.valueof(all));
        }
    }
}