package sprites;

import slythr.Primitive;
import slythr.Rect;
import stardust.GlobalGamestate;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by teddy on 5/15/17.
 */
public class LittleStar {

    static ArrayList<LittleStar> spritelist = new ArrayList<>();
    Primitive self_primitive;
    static GlobalGamestate globalGamestate;
    static Random rand = new Random();
    static Frame host_frame;
    static LittleStar tadd;

    public static void initialize(){
        for (int i = 0; i < 15; i++){
            spritelist.add(new LittleStar());
        }
    }

    public static void bind_gamestate(GlobalGamestate gamestate){
        globalGamestate = gamestate;
    }

    public static void bind_host_frame(Frame frame){
        host_frame = frame;
    }

    public LittleStar(){
        this.self_primitive = new Rect();
        this.self_primitive.setColor(255, 255, 255);
        //self_primitive.setHeight(5);
        //self_primitive.setWidth(5);
        globalGamestate.physics_enable(self_primitive);
        this.self_primitive.setpos(rand.nextInt(host_frame.getWidth()) + 5, rand.nextInt(host_frame.getHeight()));
        this.self_primitive.setHeight(5);
        this.self_primitive.setWidth(5);
        self_primitive.setPhysics_velocity_y(rand.nextInt(GlobalGamestate.gamevar_maxlittlestarspeed) + GlobalGamestate.gamevar_minlittlestarspeed);
        //self_primitive.setPhysics_velocity_y(rand.nextInt(20) + 3);
        this.self_primitive.setPhysics_velocity_y(10);
        this.self_primitive.setLabel("one of the background stars");
    }

    public static void behave(){
        for (LittleStar instance : spritelist){
            if (instance.self_primitive.getpos()[1] > host_frame.getHeight()){
                instance.self_primitive.setpos(rand.nextInt(host_frame.getWidth()) + 5, -10);
                instance.self_primitive.setPhysics_velocity_y(rand.nextInt(GlobalGamestate.gamevar_maxlittlestarspeed) + GlobalGamestate.gamevar_minlittlestarspeed);

            }
        }
    }

    public static void draw(Graphics g){
        for (LittleStar instance : spritelist){
            instance.self_primitive.draw(g);
        }
    }

    public static void flush(){
        spritelist.clear();
    }

}
