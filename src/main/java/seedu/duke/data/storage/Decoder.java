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
import seedu.duke.ui.TextUi;

/**
 * Manages all outputs from files, and the conversion from String in file to Object in memory.
 * Throws exceptions to InputOutputManager and handles none.
 *
 * @author Sim Jun You
 * @author Wang Qin
 */

public class Decoder {
    /**
     * Loads a HashMap of Module objects from the specified file. Used for both user and NUS modules.
     *
     * @param dataFileName
     *  The file to load from
     * @return
     *  The HashMap of Module objects
     */
    public static HashMap<String, Module> loadModules(String dataFileName) {
        String jsonStr;
        jsonStr = loadJsonStringFromFile(dataFileName);
        TextUi.outputToUser(dataFileName);
        // FastJSON doesn't write the square brackets for some reason when we save, so we add it in here
        // so that parseArray works as it should
        if (jsonStr != null) {
            jsonStr = "[" + jsonStr + "]";
        }
        List<Module> moduleList = JSON.parseArray(jsonStr, Module.class);
        HashMap<String, Module> modulesMap = new HashMap<>();
        if (moduleList != null) {
            for (Module eachModule : moduleList) {
                modulesMap.put(eachModule.getModuleCode(), eachModule);
            }
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
    public static ArrayList<Task> loadTasks(String dataFileName) {
        String jsonStr;
        jsonStr = loadJsonStringFromFile(dataFileName);
        // FastJSON doesn't write the square brackets for some reason when we save, so we add it in here
        // so that parseArray works as it should
        if (jsonStr != null) {
            jsonStr = "[" + jsonStr + "]";
        }
        List<Task> tasksList = JSON.parseArray(jsonStr, Task.class);// extractModules(jsonStr);
        return new ArrayList<>(tasksList);
    }

    /**
     * Pulls JSON from the NUSMods API, parses it, and returns the HashMap of Module objects.
     *
     * @return
     *  The HashMap of Module objects (from NUSMods)
     */
    public static HashMap<String, Module> generateNusModsList() {
        HashMap<String, Module> retrievedNusModsList = new HashMap<>();
        String retrievedJson;
        retrievedJson = requestNusModsJsonString("https://api.nusmods.com/v2/2019-2020/moduleList.json");
        // This JSON string comes with the square brackets, so no need to add
        List<Module> modulesList = JSON.parseArray(retrievedJson, Module.class);// extractModules(jsonStr);

        for (Module eachModule : modulesList) {
            retrievedNusModsList.put(eachModule.getModuleCode(), eachModule);
        }

        return retrievedNusModsList;
    }

    /**
     * Reads a string from a file (doesn't necessarily have to be JSON).
     *
     * @param dataFileName
     *  The specified file
     * @return
     *  The string read from file
     */
    private static String loadJsonStringFromFile(String dataFileName) {
        File file = new File(dataFileName);
        long fileLength = file.length();
        byte[] fileContent = new byte[(int) fileLength];

        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
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
        System.out.println("Getting stuff from NUSMods");
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
