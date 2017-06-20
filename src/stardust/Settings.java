package stardust;

import slythr.Audio;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by teddy on 6/11/17.
 */
public class Settings {

    /**
     * ArrayList containing the settings contained in the settings.pref file.
     */
    public static ArrayList<String> settings_list = new ArrayList<>();


    /**
     * Load settings from settings.pref file into the settings_list
     * @throws IOException
     */
    public static void read() throws IOException {
        String prefData = new String(Files.readAllBytes(FileSystems.getDefault().getPath(SaveGame.get_savedat_path("settings.pref"))), StandardCharsets.UTF_8);
        for (String setting : prefData.split(";")){
            settings_list.add(setting);
        }
    }

    /**
     * Set the value of the setting {@code id}
     * @param id id of the value to set
     * @param value value to set id to
     * @throws FileNotFoundException
     */
    public static void set(String id, String value) throws FileNotFoundException {
        for (String setting : settings_list){
            if (setting.split("=")[0].contains(id)) {
                settings_list.set(settings_list.indexOf(setting), id + "=" + value);
            }
        }
        PrintWriter writer = new PrintWriter(SaveGame.get_savedat_path("settings.pref"));
        writer.print("");
        writer.close();
        PrintStream fout = new PrintStream(new FileOutputStream(GlobalGamestate.localizePath(SaveGame.get_savedat_path("settings.pref"))));
        for (String setting : settings_list){
            fout.print(setting);
            fout.println(";");
        }
        fout.close();
    }

    /**
     * Get the value of a setting
     * @param id the id of a setting
     * @return a string of the value of the setting
     */
    public static String get(String id){
        for (String setting : settings_list) {
            String[] splitted_setting = setting.split("=");
            if (splitted_setting[0].equals(id)) {
                return splitted_setting[1];
            }
        }
        return "error";
    }


    /**
     * Load the settings file into settings_list and use these values to set settings in the game
     * @throws IOException
     */
    public static void load() throws IOException {
        read();
        Audio.set_volume(Double.parseDouble(get("volume")));
    }



}
