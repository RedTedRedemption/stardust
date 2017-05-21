package stardust;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by teddy on 4/24/17.
 */
public class SaveGame {

    static String save_String;



    public static int save(String target_file, String level, int step) throws IOException {


        save_String = level + ";" + Integer.toString(step) + ";" + Integer.toString(MainPane.cvar_saveslot);
        System.out.println("save string is " + save_String);

        PrintStream fout = new PrintStream(new FileOutputStream(GlobalGamestate.localizePath(get_savedat_path(target_file))));
        fout.print(save_String);




        return 1;

    }

    public static void load(String source_file) throws IOException {
        System.out.print("loading file...");
        String savedata = new String(Files.readAllBytes(FileSystems.getDefault().getPath(get_savedat_path(source_file))), StandardCharsets.UTF_8);
        System.out.println(savedata);
        String[] splitted_savedata = savedata.split(";");

        MainPane.set_level(splitted_savedata[0]);
        for (String each : splitted_savedata){
            System.out.println(each);
        }
        MainPane.currentLevel.step = Integer.parseInt(splitted_savedata[1]);
        MainPane.cvar_saveslot = Integer.parseInt(splitted_savedata[2]);



    }

    public static String get_savedat_path(String filename){
        String OS = System.getProperty("os.name");
        if (OS.contains("win")){
            try {
                return Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\dat\\" + filename;
            } catch (Exception e){
                return Paths.get(".").toAbsolutePath().normalize().toString() + "\\" + filename;
            }
        }
        try {
            return Paths.get(".").toAbsolutePath().normalize().toString() + "/src/dat/" + filename;
        } catch (Exception e){
            return Paths.get(".").toAbsolutePath().normalize().toString() + "/" + filename;
        }
    }

}
