package levels;

import jdk.nashorn.internal.objects.Global;
import slythr.Stack;
import sprites.EnemySprite;
import sprites.Sprite;
import stardust.GlobalGamestate;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by teddy on 3/5/17.
 */
public class Level {

    String source_file;
    public ArrayList<String> sequence = new ArrayList<String>();
    int step = 0;
    Frame host_frame;
    GlobalGamestate globalGamestate;
    String addme;

    public Level(String sourcefile, Frame frame, GlobalGamestate globalgamestate){
        host_frame = frame;
        System.out.print("Adding new level from file '" + sourcefile + "'...");
        source_file = sourcefile;
        globalGamestate = globalgamestate;
        System.out.println("done");

    }
    public void load() throws FileNotFoundException {
        System.out.print("loading level from file '" + source_file + "'...");
        Scanner scan = new Scanner(new File(source_file));
        while (scan.hasNext()){
            addme = scan.next();
            if(addme.contains("wait")){
                String[] addmelist = addme.split("-");
                if (addmelist.length > 1){
                    for (int addwait = Integer.parseInt(addmelist[1]); addwait > 0; addwait--){
                        sequence.add("wait");
                    }
                }
            } else {
                sequence.add(addme);
            }

        }
        sequence.add("EOL");
        System.out.println("done");
    }

    public boolean step(EnemySprite enemy){
        String spawnline = sequence.get(step);
        //System.out.println("instruction is " + spawnline);
        step = step + 1;
        if (spawnline.contains("enemy")) {
            String[] splitted_spawnline = spawnline.split("-");
            System.out.println("spawning " + splitted_spawnline[1] + " enemies");
            for (int spawncount = Integer.parseInt(splitted_spawnline[1]); spawncount > 0; spawncount --) {
                enemy.instantiate(host_frame, globalGamestate);
            }
        }
        if (spawnline.contains("EOL")){
            return false;
        }
        if (spawnline.contains("suspend")){
            step = step - 1;
            return true;
        }
        return true;
    }

    public void reset(){
        step = 0;
    }

    public boolean spawn(){
        String spawnline = sequence.get(0);
        step = step + 1;
        if (spawnline.contains("enemy")) {
            return true;
        } else {
            return false;
        }
    }

}
