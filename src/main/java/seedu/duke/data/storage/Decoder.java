package seedu.duke.data.storage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import seedu.duke.data.Module;
import seedu.duke.data.Task;
import seedu.duke.data.TimeTable;
import seedu.duke.exception.NusModsNotLoadedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static seedu.duke.data.storage.InputOutputManager.JAR_NUS_MODULE_FILE;

/**
 * Manages all outputs from files, and the conversion from String in file to Object in memory.
 * Throws exceptions to InputOutputManager and handles none.
 *
 * @author Sim Jun You
 * @author Wang Qin
 */

public class Decoder {
    public static TimeTable loadTimeTable(String dataFileName) throws IOException, JSONException {
        String jsonStr;
        jsonStr = loadJsonStringFromFile(dataFileName);
        TimeTable timetable = JSON.parseObject(jsonStr, TimeTable.class);
        return timetable;
    }

    /**
     * Loads a HashMap of Module objects from the specified file. Used for both user and NUS modules.
     *
     * @param dataFileName
     *  The file to load from
     * @return
     *  The HashMap of Module objects
     * @throws JSONException
     *  When the file to be parsed has caused some error
     * @throws IOException
     *  When the file cannot be loaded or read
     */
    public static HashMap<String, Module> loadModules(String dataFileName) throws IOException, JSONException {
        String jsonStr;
        jsonStr = loadJsonStringFromFile(dataFileName);
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
     * @throws JSONException
     *  When the file to be parsed has caused some error
     * @throws IOException
     *  When the file cannot be loaded or read
     */
    public static ArrayList<Task> loadTasks(String dataFileName) throws IOException, JSONException {
        String jsonStr;
        jsonStr = loadJsonStringFromFile(dataFileName);

        if (jsonStr != null) {
            String[] lines = jsonStr.split("\\r?\\n");
            ArrayList<Task> tasksList = new ArrayList<Task>();
            for (String eachTaskStr : lines) {
                tasksList.add(JSON.parseObject(eachTaskStr, Task.class));
            }
            return tasksList;
        } else {
            return new ArrayList<Task>();
        }
    }

    /**
     * Loads NUSMods list from the JAR file's resources folder.
     *
     * @return
     *  The HashMap of Module objects (from NUSMods)
     * @throws IOException
     *  When the file cannot be read
     * @throws JSONException
     *  When the file to be parsed has caused some error
     * @throws NullPointerException
     *  When the file cannot be loaded
     */
    public static HashMap<String, Module> loadNusModsFromJar() throws IOException, JSONException, NullPointerException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                Decoder.class.getResourceAsStream(JAR_NUS_MODULE_FILE)));
        String jsonStr = "";
        while (br.ready()) {
            jsonStr += br.readLine();
        }
        if (jsonStr != null) {
            jsonStr = "[" + jsonStr + "]";
        }
        List<Module> modulesList = JSON.parseArray(jsonStr, Module.class);
        HashMap<String, Module> retrievedNusModsList = new HashMap<>();
        for (Module eachModule : modulesList) {
            retrievedNusModsList.put(eachModule.getModuleCode(), eachModule);
        }
        return retrievedNusModsList;
    }

    /**
     * Pulls JSON from the NUSMods API, parses it, and returns the HashMap of Module objects.
     *
     * @return
     *  The HashMap of Module objects (from NUSMods)
     * @throws ConnectException
     *  When the connection to the NUSMods API fails
     * @throws JSONException
     *  When the JSON data cannot be parsed
     */
    public static HashMap<String, Module> generateNusModsList() throws ConnectException, JSONException {
        HashMap<String, Module> retrievedNusModsList = new HashMap<>();
        String retrievedJson;
        retrievedJson = requestNusModsJsonString("https://api.nusmods.com/v2/2020-2021/moduleList.json");
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
     * @throws IOException
     *  When the file cannot be loaded or read
     */
    private static String loadJsonStringFromFile(String dataFileName) throws IOException {
        File file = new File(dataFileName);
        long fileLength = file.length();
        byte[] fileContent = new byte[(int) fileLength];

        FileInputStream in = new FileInputStream(file);
        in.read(fileContent);
        in.close();

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
     * @throws ConnectException
     *  When the connection to the NUSMods API fails
     */
    private static String requestNusModsJsonString(String filePath) throws ConnectException {
        int httpResult; // the status from the server response
        String content = "";

        try {
            URL url = new URL(filePath); // create URL
            URLConnection urlConn = url.openConnection(); // try to connect and get the status code
            urlConn.connect();
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpResult = httpConn.getResponseCode();
            if (httpResult != HttpURLConnection.HTTP_OK) {
                throw new ConnectException();
            } else {
                int fileSize = urlConn.getContentLength(); // get the length of the data
                InputStreamReader isReader = new InputStreamReader(urlConn.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(isReader);
                StringBuffer buffer = new StringBuffer();
                String line; // to save the content of every line
                line = reader.readLine(); // read the first line
                while (line != null) { // if line is empty, means finish reading
                    buffer.append(line); // append to the buffer
                    buffer.append(" "); // add new line
                    line = reader.readLine(); // read the next line
                }
                content = buffer.toString();
            }
        } catch (Exception e) {
            throw new ConnectException();
        }

        return content;
    }
}
