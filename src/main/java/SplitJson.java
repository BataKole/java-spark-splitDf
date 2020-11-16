import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SplitJson {

    public static void main(String[] args) throws FileNotFoundException {

        // First, ensure there are 2 args
        if (args.length != 2) {
            throw new IllegalArgumentException("Exactly 2 parameters required !");
        }
        String jsonPath = args[0];
        int partsNum = Integer.parseInt(args[1]);


        JSONParser jsonParser = new JSONParser();

        JSONArray employeeList = new JSONArray();

        JSONArray temp = new JSONArray();


        // Load Json file
        try (FileReader reader = new FileReader(jsonPath)){

            Object obj = jsonParser.parse(reader);

            employeeList = (JSONArray) obj;

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int totalSize = employeeList.size();
        int partSize = (totalSize / partsNum) + 1;


        int i=1;
        int j=1;
        for (Object jsonObj:employeeList) {

            temp.add(jsonObj);

            if(i % partSize == 0 || i  >= totalSize) {

                System.out.println("i = " + i);
                System.out.println("partSize = " + partSize);
                System.out.println("totalSize = " + totalSize);


                // Write Jsons
                try (FileWriter file = new FileWriter("out" + j +".json")) {

                    file.write(temp.toJSONString());
                    file.flush();
                    System.out.println("Part " + j + " written.");
                    j++;

                } catch (IOException e) {
                    e.printStackTrace();
                }
                temp.clear();
            }


            i++;
        }
    }
}
