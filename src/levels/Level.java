package levels;

import slythr.Complex_Stack;
import slythr.TextboxThread;
import slythr.Title;
import sprites.AsteroidSprite;
import sprites.EnemySprite;
import stardust.GameLoop;
import stardust.GlobalGamestate;
import stardust.MainPane;
import stardust.SaveGame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;

/**
 * Created by teddy on 3/5/17.
 */

public class Level {

    String source_file;
    public ArrayList<String> sequence = new ArrayList<String>();
    public int step = 0;
    Frame host_frame;
    GlobalGamestate globalGamestate;
    boolean runagain = true;
    Thread titlethread;


    public Level(String sourcefile, Frame frame, GlobalGamestate globalgamestate){
        host_frame = frame;
        //System.out.print("Adding new level from file '" + sourcefile + "'...");
        source_file = sourcefile;
        globalGamestate = globalgamestate;
        System.out.println("done");

    }
    public void load() throws IOException {
        System.out.print("loading level from file '" + source_file + "'...");
        sequence.clear();
        try {
            String leveldata = new String(Files.readAllBytes(FileSystems.getDefault().getPath(source_file)), StandardCharsets.UTF_8);
            String[] steps = leveldata.split(";");
            //System.out.println(steps[1]);
            for (String step : steps) {                               // 0  1
                String[] line = step.split(" ");                //wait 2
                if (step.contains("wait")) {
                    if (line.length > 1) {
                        for (int waits = 0; waits <= Integer.parseInt(line[1]); waits++) {
                            sequence.add("wait");
                        }
                    } else {
                        sequence.add("wait");
                    }
                } else {
                    sequence.add(step);
                }
            }
            sequence.add("EOL");
            System.out.print("cleaining up sequence...");
            System.out.println("done");
        } catch (Exception e){
                JOptionPane.showMessageDialog(new Frame(), e.toString());
        }
    }

    public boolean step(Graphics g, Complex_Stack complex_stack) throws IOException {
        String spawnline = sequence.get(step);
        //System.out.println(spawnline);
        //System.out.println("instruction is " + spawnline);
        step = step + 1;
        try {
            if (spawnline.contains("enemy")) {
                String[] splitted_spawnline = spawnline.split(" ");
                //System.out.println("spawning " + splitted_spawnline[1] + " enemies");
                for (int spawncount = Integer.parseInt(splitted_spawnline[1]); spawncount > 0; spawncount--) {
                    EnemySprite.instantiate();
                }
            }
            if (spawnline.contains("EOL")) {
                return false;
            }
            if (spawnline.contains("suspend")) {
                step = step - 1;
                return true;
            }
            if (spawnline.contains("jump")) {
                String[] splitted_spawnline = spawnline.split(" ");
                step = Integer.parseInt(splitted_spawnline[1]);
                return true;
            }
            if (spawnline.contains("textbox")) {
                GameLoop.dialogue_start = Instant.now();
                String[] splitted_spawnline = spawnline.split("-");
                //System.out.println("making a textbox with content " + " " + splitted_spawnline[1] + " " + splitted_spawnline[2] + " " + splitted_spawnline[3]);

                Thread waitThread = new Thread(new TextboxThread(GlobalGamestate.gamevar_dialoguedelay, MainPane.make_text_box(globalGamestate, g, splitted_spawnline[1], splitted_spawnline[2], splitted_spawnline[3], host_frame)));
                waitThread.start();
                waitThread.join();
                return true;
            }
            if (spawnline.contains("stop_time")) {
                MainPane.stoptime();
                return true;
            }
            if (spawnline.contains("asteroid")) {
                String[] splitted_spawnline = spawnline.split(" ");
                //System.out.println("spawning " + splitted_spawnline[1] + "asteroids");
                for (int spawncount = Integer.parseInt(splitted_spawnline[1]); spawncount > 0; spawncount--) {
                    AsteroidSprite.instantiate();
                }
            }
            if (spawnline.contains("disarm_weapons")) {
                GlobalGamestate.disarm_weapons();
            }
            if (spawnline.contains("arm_weapons")) {
                GlobalGamestate.arm_weapons();
            }
            if (spawnline.contains("change_level")) {
                String[] splitted_spawnline = spawnline.split(" ");
                step = 0;

                MainPane.set_level(Level.get_level_path(splitted_spawnline[1]));
            }
            if (spawnline.contains("god_on")) {
                globalGamestate.god_on();
            }
            if (spawnline.contains("god_off")) {
                GlobalGamestate.statevar_god = false;
            }
            if (spawnline.equals("wait")) {
                //pass;
//        } else {
//            System.out.print("Error at line ");
//            System.out.print(step);
//            System.out.print(" in level ");
//            System.out.print(source_file);
//            System.out.print(" - Command not found: ");
//            System.out.println(spawnline);
            }
            if (spawnline.contains("start_time")) {
                MainPane.starttime();
            }
            if (spawnline.contains("clear_text_box")) {
                MainPane.dialoguebox_stack.flush();
            }
            if (spawnline.contains("enter_cutscene")) {
                MainPane.stoptime();
                MainPane.cvar_gamestate = 4;
            }
            if (spawnline.contains("exit_cutscene")) {
                MainPane.starttime();
                MainPane.cvar_gamestate = 1;
                MainPane.dialoguebox_stack.flush();
            }
            if (spawnline.contains("set_cutscene_back")) {
                String[] splitted_spawnline = spawnline.split(" ");
                MainPane.cutscene_background.setImage(splitted_spawnline[1]);
            }
            if (spawnline.contains("quicksave")) {
                System.out.print("Quicksaving...");

                System.out.println("done");
            }
            if (spawnline.contains("savegame")) {
                String slotfilename = "slot_" + Integer.toString(MainPane.cvar_saveslot) + ".sav";
                System.out.print("saving the game to file " + slotfilename + "...");
                SaveGame.save(GlobalGamestate.localizePath((FileSystems.getDefault().getPath(slotfilename).toString())), source_file, step);
                System.out.println("done");
            }
            if (spawnline.contains("title")) {
                String[] splitted_spawnline = spawnline.split("-");
                Title title = new Title("empty", 3000);
                title.show(splitted_spawnline[1], Integer.parseInt(splitted_spawnline[2]), MainPane.global_g);
            }
            if (spawnline.contains("print")) {
                String[] splitted_spawnline = spawnline.split("-");
                System.out.println(splitted_spawnline[1]);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(MainPane.host_frame, "The level reader has encountered an error while reading "
                                                  + source_file + " \nThis may be an error in the level file, look to line "
                                                  + Integer.toString(step) + ": " + spawnline + "\n\nThe VM threw " + e,
                                          "Level Error",
                                          JOptionPane.ERROR_MESSAGE);
        }

        return true;
    }

    public void reset(){
        step = 0;
    }

    public boolean spawn(){
        String spawnline = sequence.get(0);
        step = step + 1;
        return spawnline.contains("enemy");
    }

    public static String get_level_path(String filename){
        String OS = System.getProperty("os.name");
        if (OS.contains("win")){
            try {
                return Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\levels\\" + filename;
            } catch (Exception e){
                return Paths.get(".").toAbsolutePath().normalize().toString() + "\\" + filename;
            }
        }
        try {
            return Paths.get(".").toAbsolutePath().normalize().toString() + "/src/levels/" + filename;
        } catch (Exception e){
            return Paths.get(".").toAbsolutePath().normalize().toString() + "/" + filename;
        }
    }

}
