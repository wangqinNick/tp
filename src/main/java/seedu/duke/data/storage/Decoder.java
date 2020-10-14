package seedu.duke.data.storage;

import seedu.duke.data.Task;
import seedu.duke.data.Module;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * Manages all outputs from files, and the conversion from String in file to Object in memory.
 * Throws exceptions to InputOutputManager and handles none.
 *
 * @author Sim Jun You
 * @author Wang Qin
 */
public class Decoder {

    public static HashMap<String, Module> loadModules(String dataFileName) {
        String jsonStr;
        jsonStr = loadJsonStringFromFile(dataFileName);
        List<Module> moduleList = JSON.parseArray(jsonStr, Module.class);// extractModules(jsonStr);
        HashMap<String, Module> modulesMap = new HashMap<>();
        for (Module eachModule : moduleList) {
            modulesMap.put(eachModule.getCode(), eachModule);
        }
        return modulesMap;
    }

    /**
     * Parses the specified save file to return an ArrayList of Task objects.
     *
     * @param dataFileName
     *  The name of the file to read from
     * @return
     *  The ArrayList tasksList
     * @throws FileNotFoundException
     *  When the file does not exist
     */
    public static ArrayList<Task> loadTasks(String dataFileName) throws FileNotFoundException {
        String jsonStr;
        jsonStr = loadJsonStringFromFile(dataFileName);
        List<Task> tasksList = JSON.parseArray(jsonStr, Task.class);// extractModules(jsonStr);
        return new ArrayList<>(tasksList);
    }


    private static String loadJsonStringFromFile(String dataFileName) {
        File file = new File(dataFileName);
        long fileLength = file.length();
        byte[] fileContent = new byte[(int) fileLength];

        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        } catch (FileNotFoundException e) {
            //System.out.println("Retrieving the module list from nusmods...");
            return requestNusModsJsonString("https://api.nusmods.com/v2/2019-2020/moduleList.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String encoding = "utf8";
        try {
            return new String(fileContent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Uses the NUSMods API to get a JSON string with the information of all available mods.
     *
     * @param filePath
     *  The endpoint for the NUSMods API.
     * @return
     *  The JSON string with information of all currently available mods in NUS.
     */
    private static String requestNusModsJsonString(String filePath) {
        int httpResult; // the status from the server response
        String content = "";
        try {
            URL url = new URL(filePath); // create URL
            URLConnection urlConn = url.openConnection(); // try to connect and get the status code
            urlConn.connect();
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpResult = httpConn.getResponseCode();
            if (httpResult != HttpURLConnection.HTTP_OK) {
                System.out.print("cannot connect!");
            } else {
                int fileSize = urlConn.getContentLength(); // get the length of the data
                InputStreamReader isReader = new InputStreamReader(urlConn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(isReader);
                StringBuffer buffer = new StringBuffer();
                String line; // to save the content of every line
                line = reader.readLine(); // read the first line
                while (line != null) { // if line is empty, means finish reading
                    buffer.append(line); // append to the buffer
                    buffer.append(" "); // add new line
                    line = reader.readLine(); // read the next line
                }
                //System.out.print(buffer.toString());
                content = buffer.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
