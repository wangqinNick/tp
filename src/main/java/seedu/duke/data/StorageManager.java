package seedu.duke.data;

import seedu.duke.directory.Module;
import seedu.duke.exception.CorruptedFileException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StorageManager {
    private static boolean isToSave = false;
    private String dataFileName;

    /**
     * Checks whether to save the list.
     *
     * @return
     *  The indication to save the list
     */
    public static boolean isToSave() {
        return isToSave;
    }

    /**
     * Indicates to the storage manager that a change is made to the list and needs to be saved.
     */
    public static void setIsSave() {
        isToSave = true;
    }

    /**
     * Constructor with name of the data file as argument.
     *
     * @param dataFileName the name of the file in which the data is stored.
     */
    public StorageManager(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    /**
     * Saves the Module List into a file.
     */
    public void saveList() {
        try {
            File saveFile = new File(dataFileName);
            saveFile.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(saveFile);
            fileWriter.write(new Encoder(ModuleManager.getModuleList()).encode());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ignored) {

        }
    }

    /**
     * Loads the Module List from the saved file.
     */
    public void loadList() {
        try {
            FileReader fileReader = new FileReader(dataFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList<Module> moduleList = new Decoder(bufferedReader).decode();
            ModuleManager.setModuleList(moduleList);
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            ModuleManager.setModuleList(new ArrayList<>());
        } catch (CorruptedFileException | ArrayIndexOutOfBoundsException e) {
            System.out.println("File is corrupted!\n");
            ModuleManager.setModuleList(new ArrayList<>());
        }
    }

    /**
     * Clean up all extra files from the files folder.
     */
    public void cleanUp() throws IOException {
        // Get all the names of the saved files
        File[] savedFiles = new File(StoragePath.TASK_FILE_DIRECTORY_PATH).listFiles();
        if (savedFiles == null) {
            return;
        }
        ArrayList<String> savedFileNames =
                Stream.of(savedFiles).map(File::getName).collect(Collectors.toCollection(ArrayList::new));
    }
}

