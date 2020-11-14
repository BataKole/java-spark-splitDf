import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SplitJson {

    public static void main(String[] args) throws FileNotFoundException {

        JSONParser jsonParser = new JSONParser();

        JSONArray employeeList = new JSONArray();

        JSONArray temp = new JSONArray();


        // Load Json file
        try (FileReader reader = new FileReader("./data/generated.json")){

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
        int partsNum = 5;
        int partSize = (totalSize / partsNum);


        int i=1;
        for (Object jsonObj:employeeList) {

            temp.add(jsonObj);

            if(i % partSize == 0) {

                // Write Jsons
                try (FileWriter file = new FileWriter("out" + i / partSize +".json")) {

                    file.write(temp.toJSONString());
                    file.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                temp.clear();
            }

            i++;
        }





    }
}
